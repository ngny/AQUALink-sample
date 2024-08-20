package com.ngnydevices.open.a9000p;


import com.ngnydevices.open.a9000p.errors.DataTransmissionException;
import com.ngnydevices.open.a9000p.errors.TimeoutException;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class AstmThread extends Thread implements  Runnable,PayloadListener {

    private final BasicLogger log = new BasicLogger(RawFrame.class.getName());

    private final String host;
    private final int port;
    private TcpStateMachine tcp;
    private boolean stopped;
    private final boolean server;
    private boolean ready;
    private Status status;
    private PayloadListener listener;
    private final ArrayList<Payload> receivedPayloads = new ArrayList<>();

    public static final Object receivedMutex = new Object();


    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setListener(PayloadListener listener) {
        this.listener = listener;
    }

    public Payload request(Payload tmp, String explanation, int timeoutSend, int timeoutReceive) throws Exception, TimeoutException, DataTransmissionException {

        synchronized (this) {
            synchronized (receivedPayloads) {
                receivedPayloads.clear();
             }

            getTcp().pushPayload(tmp, explanation, timeoutSend);

            if (timeoutReceive<0) return null;

            long alarm = TimeHelper.now() + timeoutReceive;
            while(TimeHelper.now() < alarm) {

                synchronized (receivedPayloads) {
                    if (receivedPayloads.size()==1) {
                        return receivedPayloads.remove(0);
                    }
                }

                Thread.sleep(10);
            }
            throw new TimeoutException();
        }
    }

    @Override
    public void onReceivedPayload(TcpStateMachine tcp, Payload q) {
        synchronized (receivedMutex) {

            if (receivedPayloads.size()>10) {
                receivedPayloads.remove(0);
            }
            receivedPayloads.add(q);
        }

        if (listener!=null) {
            listener.onReceivedPayload(tcp,q);
        }
    }

    public void kill() {
        stopped = true;
        this.interrupt();
    }


    public enum Status {
        Stopped,
        StartedWaitingForLis,
        Starting, StartedReady
    }


    public AstmThread(boolean server, String host, int port) {
        super();
        this.server = server;
        this.host = host;
        this.port = port;
    }

    public TcpStateMachine getTcp() {
        return tcp;
    }

    public int getPort() {
        return port;
    }

    @Override
    public void run() {


        while (!stopped) {

            try {

                if (server) {
                    log.info("Server [START]");
                    runServer();
                    log.info("Server [STOP]");
                } else {
                    log.info("Client [START]");
                    runClient();
                    log.info("Client [STOP]");
                }


            } catch (Throwable th) {


                if (server) {
                    log.info("Server [ERROR] port:" + getPort());
                } else {
                    log.info("Client [ERROR] port:" + getPort());
                }


                th.printStackTrace();
                
                for (int i = 0; i < 30 && !stopped; i++)
                    try {
                        TimeHelper.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        }

    }

    public String getHost() {
        return host;
    }

    private void runClient() {

        setReady(false);
        setStatus(Status.StartedWaitingForLis);

        while (!Thread.currentThread().isInterrupted() && !stopped) {

            tcp = null;
            setReady(false);
            log.info("======== MAIN LOOP =========");
            Socket socket = null;
            try {
                log.info("Connecting to: " + getHost() + ":" + getPort());
                socket = new Socket(getHost(), getPort());
                log.info("Connected...");
                tcp = new TcpStateMachine(getID(), socket, TcpStateMachine.Mode.INS, this, 1, "A9000P-Client");
                setReady(true);
                setStatus(Status.StartedReady);
                tcp.run();
                setStatus(Status.StartedWaitingForLis);
                socket.close();
                socket = null;

            } catch (IOException e) {
                setStatus(Status.StartedWaitingForLis);
                setReady(false);
                e.printStackTrace();

                try {
                    TimeHelper.sleep(3*1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }


            } finally {
                setStatus(Status.StartedWaitingForLis);
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            try {
                TimeHelper.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }
        setStatus(Status.Stopped);

    }

    private String getID() {
        return getHost()+":"+port+":"+(server?"server":"client");
    }


    private void runServer() throws Throwable {



        setStatus(Status.StartedWaitingForLis);
        ServerSocket serverSocket = null;

        log.info(getHost() + " Opening port:" + getPort());
        try {

            serverSocket = new ServerSocket(getPort());

            while (!stopped) {
                Socket clientSocket;
                try {
                    serverSocket.setSoTimeout(2000);
                    log.info("Waiting for client to connect during 2s...");
                    clientSocket = serverSocket.accept();

                } catch (Throwable e) {
                    log.info("No client");
                    if (Thread.interrupted()) {
                        break;
                    }
                    continue;
                }

                log.info("client [BEGIN]");
                tcp = null;
                try {
                    setStatus(Status.StartedReady);

                    tcp = new TcpStateMachine(getID(), clientSocket, TcpStateMachine.Mode.LIS, this, 1
                            , "A9000P");
                    setReady(true);
                    tcp.run();
                    setStatus(Status.StartedWaitingForLis);
                    clientSocket.close();
                    clientSocket = null;
                    tcp = null;
                    log.info("client [OK]");

                } catch (Throwable e) {
                    setStatus(Status.StartedWaitingForLis);

                    if (clientSocket != null) {
                        try {
                            clientSocket.close();
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                    e.printStackTrace();
                    if (Thread.interrupted()) {
                        break;
                    }
                    TimeHelper.sleep(1000);
                    tcp = null;
                } finally {
                    setReady(false);

                }
            }

        } finally {


            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (Throwable th) {
                    log.warn(th.getMessage(), th);
                }
            }

        }

    }




    public boolean getStopped() {
        return stopped;
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public boolean getReady() {
        return ready;
    }
}
