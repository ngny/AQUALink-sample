package com.ngnydevices.open.a9000p;


import com.google.common.primitives.Bytes;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;


public class TcpDataLinkLayer extends GenericDataLinkLayer {

    final private Object lock = new Object();
    private Socket commSocket;
    private final BufferedOutputStream writer;
    private final BufferedInputStream reader;
    private String host = "U";

    private final BasicLogger log = new BasicLogger(TcpDataLinkLayer.class.getName());

    public TcpDataLinkLayer(Socket s) throws IOException {
        super();
        commSocket = s;
        commSocket.setSoTimeout(10);
        writer = new BufferedOutputStream(s.getOutputStream());
        reader = new BufferedInputStream(s.getInputStream());

    }

 

    @Override
    public void close() {
        synchronized (lock) {
            try {
                if (commSocket != null) {
                    commSocket.close();
                    commSocket = null;
                }
            } catch (IOException e) {
                log.warn(e.getMessage(), e);
            }
        }
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public void writeByte(byte b) throws Exception {

        synchronized (lock) {
            if (commSocket == null) {
                return;
            }


            writer.write(b);
            writer.flush();

            verbose(this.host + " W " +ASTM.niceByteRepresentation(new byte[]{b}));


        }

    }



    @Override
    public byte[] readBytes(byte end) throws IOException {
        byte[] ret = new byte[0];
        int n = -1;
        while (true) {
            int b;
            synchronized (lock) {
                if (commSocket == null) {
                    return null;
                }

                b = reader.read();
                if (b < 0) {
                    throw new IOException("Socket closed");
                }
            }

            ret = Bytes.concat(ret, new byte[]{(byte) b});

            if (n > 0) n--;
            if (n == 0) {

                verbose(this.host + " R " + ASTM.niceByteRepresentation(ret));

                return ret;
            }
            if (b == ASTM.ETX || b == ASTM.ETB) n = 4;
        }


    }

    @Override
    public void shortTimeout() {
        try {
            commSocket.setSoTimeout(10);
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void longTimeout() {
        try {
            commSocket.setSoTimeout(200);
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void writeBytes(byte[] data) throws IOException {
        synchronized (lock) {
            if (commSocket == null) {
                return;
            }


                writer.write(data);
                writer.flush();

                verbose(this.host + " W " + ASTM.niceByteRepresentation(data));


        }
    }

    private void verbose(String s) {
        if(s.contains(":server")) {
            return;
        }
        log.verbose(s);
    }


    @Override
    public Integer readByte() throws IOException {

        synchronized (lock) {
            if (commSocket == null) {
                return null;
            }

            try {
                int b = reader.read();



                if (b < 0) {

                    throw new IOException("Socket closed");
                }


                verbose(this.host + " R " +ASTM.niceByteRepresentation(new byte[]{(byte)b}));


                return b;
            } catch (SocketTimeoutException tex) {
                return null;
            }


        }
    }


}
