package com.ngnydevices.open.a9000p;

import com.google.common.primitives.Bytes;
import com.ngnydevices.open.a9000p.errors.DataTransmissionException;
import com.ngnydevices.open.a9000p.errors.InvalidFrameException;
import com.ngnydevices.open.a9000p.errors.TimeoutException;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;


public class TcpStateMachine implements Runnable {

    public enum State {

        BidRequest,
        CommIdle,
        FrameDataToSend,
        InterruptRequest,
        LineBid,
        ReceiveError,
        SaveFrameData,
        Stopped,
        TimeoutError,
        TransmitError,
        Uninitialized,
        WaitReceiveFrame,
        WaitTransmitReply

    }


    private static final int SECONDS = 1000;
    private final LinkedList<ByteFrame> framesToSend = new LinkedList<>();
    private final PayloadListener listener;
    private final Socket clientSocket;
    private final LinkedList<Frame> receivedFrames = new LinkedList<>();
    private final String host;
    private final ArrayList<RawFrame> rawBuffer = new ArrayList<>();
    private final int enqTime;

    private final BasicLogger log = new BasicLogger(TcpStateMachine.class.getName());

    private GenericDataLinkLayer dataLinkLayer;
    private int frameNumberTx = ASTM.INITIAL_FRAME_NUMBER;
    private State state = State.Uninitialized;
    private long sendOnlyAfter = 0;
    private long replyBefore = 0;
    private boolean busy = false;
    private ByteFrame lastTransmittedFrame;
    private RawFrame lastReceivedFrame;
    private long retry = 0;
    private int receivedFrameNumberTx = ASTM.INITIAL_FRAME_NUMBER + 1;

    private boolean ping;

    public TcpStateMachine(String host, Socket clientSocket, Mode astmMode, PayloadListener listener, int enqTime, String id) {
        this.clientSocket = clientSocket;
        this.listener = listener;
        this.host = host;
        this.enqTime = enqTime;

    }


    private String getHost() {
        return host;
    }


    public void run() {


        busy = false;

        initResources();

        try {
            dataLinkLayer = new TcpDataLinkLayer(clientSocket);
            dataLinkLayer.setHost(host);
            setState(State.Uninitialized);


            mainLoop();

        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        }


    }


    private void initResources() {


    }

    private void mainLoop() {

        State prev = null;
        long lastSecond = TimeHelper.now();


        while (true) {

            long now = TimeHelper.now();

            if (Thread.currentThread().isInterrupted()) {
                break;
            }

            if (prev != getState()) {
                prev = getState();
            }

            if (now - lastSecond >= 1000) {
                lastSecond = now;

            }

            try {
                if (getState() == State.CommIdle) {
                    TimeHelper.sleep(10);
                }
            } catch (InterruptedException e) {
                break;
            }

            try {
                processState();
            } catch (InterruptedException e) {
                log.warn("breaking ET_eventLoop");
                break;
            } catch (Throwable ex) {
                log.error(ex.getMessage(), ex);
                log.warn("breaking ET_eventLoop: exception");
                break;
            }
        }

        try {
            log.info("closing socket:" + Thread.currentThread().getId());
            dataLinkLayer.close();
            log.info("socket closed OK");
        } catch (Exception ex) {
            log.warn(ex.getMessage(), ex);
        }

        setState(State.Stopped);


    }

    private void processState() throws Exception {
        Integer rx;
        long now = TimeHelper.now();

        switch (getState()) {

            case Uninitialized: {
                sendOnlyAfter = now;
                frameNumberTx = ASTM.INITIAL_FRAME_NUMBER + 1;
                setState(State.CommIdle);
                break;
            }

            case BidRequest: {
                if (busy) {
                    replyBefore = now + randomSeconds(30);
                    send(ASTM.NAK);
                    setState(State.CommIdle);
                } else {
                    replyBefore = now + randomSeconds(30);
                    send(ASTM.ACK);

                    setState(State.WaitReceiveFrame);
                }
                break;
            }

            case ReceiveError: {

                replyBefore = now + randomSeconds(30);
                send(ASTM.NAK);
                setState(State.WaitReceiveFrame);
                break;
            }

            case InterruptRequest: {
                send(ASTM.EOT);
                sendOnlyAfter = now + randomSeconds(15);
                setState(State.CommIdle);
                break;
            }

            case TransmitError:
                if (retry >= 6) {
                    cancelPayload(Result.TransmitError);
                    send(ASTM.EOT);
                    setState(State.CommIdle);
                } else {
                    replyBefore = now + randomSeconds(15);
                    retry++;
                    sendFrame(lastTransmittedFrame);
                    setState(State.WaitTransmitReply);
                }
                break;

            case WaitTransmitReply: {
                dataLinkLayer.longTimeout();
                rx = readByte();
                if (rx == null) {

                    if (now > replyBefore) {
                        cancelPayload(Result.Timeout);
                        send(ASTM.EOT);
                        setState(State.CommIdle);
                    }

                } else if (rx.equals(ASTM.EOT)) {
                    cancelPayload(Result.CancelledByOtherSide);
                    setState(State.InterruptRequest);
                } else if (rx.equals(ASTM.ACK)) {

                    successfulFrame();


                    if (lastTransmittedFrame.isLast()) {

                        send(ASTM.EOT);
                        setState(State.CommIdle);

                    } else {
                        setState(State.FrameDataToSend);

                    }

                } else {
                    setState(State.TransmitError);
                }
                break;
            }

            case FrameDataToSend: {


                lastTransmittedFrame = getNextFrameToBeSent();

                if (lastTransmittedFrame != null) {
                    retry = 0;
                    replyBefore = now + randomSeconds(15);
                    sendFrame(lastTransmittedFrame);
                    setState(State.WaitTransmitReply);

                } else {
                    send(ASTM.EOT);
                    setState(State.CommIdle);
                }


                break;
            }


            case LineBid: {
                dataLinkLayer.longTimeout();
                rx = readByte();
                if (rx == null) {

                    if (now > replyBefore) {
                        send(ASTM.EOT);
                        sendOnlyAfter = now + randomSeconds(1);
                        setState(State.CommIdle);
                    }

                } else if (rx.equals(ASTM.ENQ)) {

                    sendOnlyAfter = now + randomSeconds(enqTime);


                    setState(State.CommIdle);
                } else if (rx.equals(ASTM.NAK)) {
                    sendOnlyAfter = now + randomSeconds(10);
                    setState(State.CommIdle);
                } else {

                    setState(State.FrameDataToSend);
                }

                break;
            }


            case SaveFrameData: {

                if (!processValidFrame(lastReceivedFrame)) {

                    setState(State.ReceiveError);

                } else {
                    replyBefore = now + randomSeconds(30);
                    send(ASTM.ACK);
                    setState(State.WaitReceiveFrame);
                }
                break;
            }


            case TimeoutError: {
                sendOnlyAfter = now;
                setState(State.CommIdle);
                break;
            }

            case WaitReceiveFrame: {
                if (now > replyBefore) {
                    setState(State.TimeoutError);
                    processPayload();

                } else {
                    try {
                        lastReceivedFrame = readFrame();

                        if (lastReceivedFrame != null) {
                            if (lastReceivedFrame.isEOT()) {
                                processPayload();
                                receivedFrames.clear();
                                sendOnlyAfter = now;
                                setState(State.CommIdle);
                            } else {
                                setState(State.SaveFrameData);
                                receivedFrameNumberTx++;
                                if (receivedFrameNumberTx >= ASTM.LAST_FRAME_NUMBER) {
                                    receivedFrameNumberTx = ASTM.INITIAL_FRAME_NUMBER;
                                }
                            }
                        }
                    } catch (Exception | InvalidFrameException th) {
                        log.warn("Invalid frame: " + th.getMessage());
                        setState(State.ReceiveError);
                    }
                }
                break;
            }

            case CommIdle:
                dataLinkLayer.shortTimeout();
                rx = readByte();
                if (ASTM.ENQ.equals(rx)) {
                    receivedFrameNumberTx = ASTM.INITIAL_FRAME_NUMBER + 1;
                    setState(State.BidRequest);
                } else if (rx == null) {
                    if (moreDataToSend() && now > sendOnlyAfter) {

                        replyBefore = now + 15 * SECONDS;
                        send(ASTM.ENQ);
                        setState(State.LineBid);
                    } else {

                        if (getPing()) {
                            setPing(false);
                            send(ASTM.ENQ);
                            replyBefore = now + randomSeconds(30);
                            setState(State.LineBid);
                        }

                        TimeHelper.sleep(10);
                    }
                }
                break;
        }


    }

    private int randomSeconds(int i) {
        return (int) (i * SECONDS - 500 + Math.random() * 1000);
    }

    private void processPayload() {

        final Payload q = new Payload();
        for (Frame receivedFrame : receivedFrames) {
            q.add(receivedFrame);
        }

        new Thread(() -> listener.onReceivedPayload(TcpStateMachine.this, q)).start();

    }

    private void successfulFrame() {

        if (lastTransmittedFrame.isLast()) {
            lastTransmittedFrame.getOriginalFrame().setResult(Result.Success);
            if (lastTransmittedFrame.getOriginalFrame().getPayload()!=null) {
                lastTransmittedFrame.getOriginalFrame().getPayload().setSent(true);
            }
        }

    }


    private void cancelPayload(Result reason) {

        log.warn(" cancel payload: reason=" + reason);
        if (lastTransmittedFrame.getOriginalFrame().getPayload()!=null) {
            lastTransmittedFrame.getOriginalFrame().getPayload().setResult(reason);
        }
        lastTransmittedFrame.getOriginalFrame().setResult(reason);
    }



    private void sendFrame(ByteFrame cf) throws IOException {


        byte[] data = cf.getBytePayload();

        if (cf.getSubframeIndex() == 0) {
            frameNumberTx = ASTM.INITIAL_FRAME_NUMBER + 1;
        }


        byte[] bytesToSend = Frame.getDataLinkBytes(frameNumberTx, data, cf.isLast());


        log.insecure("Sending " + ASTM.niceByteRepresentation(bytesToSend));

        dataLinkLayer.writeBytes(bytesToSend);

        frameNumberTx++;
        if (frameNumberTx >= ASTM.LAST_FRAME_NUMBER) {
            frameNumberTx = ASTM.INITIAL_FRAME_NUMBER;
        }


    }

    private ArrayList<byte[]> divide(byte[] data) {


        int B = ASTM.ASTM_DIVIDE_LIMIT;

        ArrayList<byte[]> ret = new ArrayList<>();

        if (data.length < B) {

            ret.add(data);

        } else {


            for (int i = 0; i < data.length; i += B) {

                int L = data.length - i;
                if (L > B) {
                    L = B;
                }

                byte[] bin = new byte[L];
                System.arraycopy(data, i, bin, 0, L);
                ret.add(bin);
            }
        }


        return ret;
    }

    private ByteFrame getNextFrameToBeSent() {
        ByteFrame f = null;
        synchronized (framesToSend) {
            if (framesToSend.size() > 0) {
                f = framesToSend.removeFirst();
            }
        }
        return f;
    }

    private boolean processValidFrame(RawFrame f) {


        try {


            rawBuffer.add(f);


            if (f.isLastBlock()) {


                byte[] x = RawFrame.concat(rawBuffer);

                int init = 0;
                for (int i = 0; i < x.length; i++) {
                    if (x[i] == ASTM.CR || i == x.length - 1) {

                        int L = 1 + i - init;
                        if (L == 0) continue;

                        Frame ret = new Frame();

                        byte[] aux = new byte[L];

                        System.arraycopy(x, init, aux, 0, L);

                        ret.parseWithoutHeader(aux);

                        receivedFrames.addLast(ret);

                        init = i + 1;
                    }
                }


                rawBuffer.clear();

            }

            return true;

        } catch (Exception ex) {
            try {
                rawBuffer.clear();
                log.error("parsing exception:" + ex.getMessage(), ex);
            } catch (Exception ex2) {
                log.warn(ex2.getMessage());
            }

            return false;
        }
    }


    private boolean moreDataToSend() {
        boolean ret = false;
        synchronized (framesToSend) {
            if (framesToSend.size() > 0) {
                ret = true;
            }
        }
        return ret;
    }

    private RawFrame readFrame() throws Exception, InvalidFrameException {


        Integer rx = dataLinkLayer.readByte();

        if (rx == null) {
            return null;
        }


        if (rx.equals(ASTM.EOT)) {

            rawBuffer.clear();

            return RawFrame.EOT();
        }

        dataLinkLayer.shortTimeout();
        byte[] buffer = dataLinkLayer.readBytes(ASTM.ETX);

        if (buffer != null) {


            byte[] bin = new byte[buffer.length + 1];
            bin[0] = rx.byteValue();
            System.arraycopy(buffer, 0, bin, 1, buffer.length);


            return new RawFrame(bin, receivedFrameNumberTx);
        }


        return null;


    }

    private void send(Integer enq) throws Exception {
        dataLinkLayer.writeByte(enq.byteValue());
    }

    private Integer readByte() throws Exception {
        return dataLinkLayer.readByte();
    }

    private State getState() {
        return state;
    }

    private void setState(State state) {
        this.state = state;



    }

    private boolean bufferEmpty() {
        synchronized (framesToSend) {
            return framesToSend.size() == 0;
        }
    }


    private ArrayList<byte[]> divide2(byte[] data) {

        ArrayList<byte[]> ret = new ArrayList<>();

        if (data.length < 230) {

            ret.add(data);

        } else {


            for (int i = 0; i < data.length; i += 230) {

                int L = data.length - i;
                if (L > 230) {
                    L = 230;
                }

                byte[] bin = new byte[L];
                System.arraycopy(data, i, bin, 0, L);
                ret.add(bin);
            }
        }


        return ret;
    }


    public synchronized void pushPayload(Payload p, String explanation,  double timeoutMs) throws Exception, TimeoutException, DataTransmissionException {


        if (!bufferEmpty() || getState() != State.CommIdle) {

            long tic = TimeHelper.now();

            long alarm = TimeHelper.now() + 5000;

            while (!bufferEmpty()) {

                if (TimeHelper.now() > alarm) {
                    log.warn("frames are not being sent");
                    throw new Exception("Frames are not being sent");
                }

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    break;
                }



            }
            long tac = TimeHelper.now();
            log.debug("[Waited] " + explanation + " " + (tac - tic) + "ms");
        } else {
            log.debug("[Buffer empty] " + explanation);
        }


        long tuc = TimeHelper.now();

        p.setExplanation(explanation);
        p.setNumRetries(p.getNumRetries() + 1);
        int size = p.getFrames().size();

        LinkedList<Frame> tmp = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            Frame f = p.getFrames().get(i);
            f.setPayload(p);
            f.setCount(size);
            tmp.add(f);
        }


        byte[] data = new byte[]{};
        for (Frame f : tmp) {
            if (data.length > 0) {
                data = Bytes.concat(data, new byte[]{13});
            }
            data = Bytes.concat(data, Bytes.concat(f.generate()));
        }



        ArrayList<byte[]> chunks = divide(data);


        ArrayList<ByteFrame> tmp2 = new ArrayList<>();

        for (int i = 0; i < chunks.size(); i++) {

            ByteFrame bf = new ByteFrame(chunks.get(i), i, chunks.size());
            bf.setOriginalFrame(tmp.get(0));
            synchronized (framesToSend) {
                tmp2.add(bf);
                if (i == chunks.size() - 1) {
                    bf.setLast(true);
                }

            }
        }

        synchronized (framesToSend) {
            for (ByteFrame b : tmp2) {
                framesToSend.addLast(b);
            }
        }

        long tic = TimeHelper.now();


        while (!p.isSent()) {

            if (p.getResult() != null) {
                throw new DataTransmissionException();
            }

            if (TimeHelper.now() - tic > timeoutMs) {
                throw new TimeoutException();
            }

            Thread.sleep(10);
        }

        long tac = TimeHelper.now();

        log.info("[sent] " + explanation + " => in " + (tac - tuc) + " ms");
    }


    public void setPing(boolean ping) {

        this.ping = ping;
    }

    public boolean getPing() {
        return ping;
    }

    public enum Mode {
        INS,
        LIS
    }


}
