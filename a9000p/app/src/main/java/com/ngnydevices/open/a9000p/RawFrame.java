package com.ngnydevices.open.a9000p;

import com.ngnydevices.open.a9000p.errors.InvalidFrameException;

import java.util.ArrayList;

public class RawFrame {
    private final byte[] data;
    private final int frameCounter;
    private boolean EOT;
    private boolean lastBlock;
    private byte[] payloadBytes;

    private final BasicLogger log = new BasicLogger(RawFrame.class.getName());


    private RawFrame() {
        data = null;
        frameCounter = 0;
    }

    public RawFrame(byte[] data, int frameCounter) throws InvalidFrameException {
        this.data = data;
        this.frameCounter = frameCounter;
        unpack();
    }

    public static RawFrame EOT() {
        RawFrame r = new RawFrame();

        r.setEOT();

        return r;

    }

    public static byte[] concat(ArrayList<RawFrame> list) {

        if (list == null) {
            return null;
        }

        if (list.size() == 1) {

            return list.get(0).getPayloadBytes();
        }

        int L = 0;
        for (RawFrame aList1 : list) {
            byte[] x = aList1.getPayloadBytes();
            if (x != null) {
                L += x.length;
            }
        }

        byte[] ret = new byte[L];
        L = 0;
        for (RawFrame aList : list) {
            byte[] x = aList.getPayloadBytes();
            if (x != null) {
                System.arraycopy(x, 0, ret, L, x.length);
                L += x.length;
            }
        }
        return ret;
    }

    public byte[] getData() {
        return data;
    }

    public boolean isLastBlock() {
        return lastBlock;
    }

    private byte[] getPayloadBytes() {
        return payloadBytes;
    }

    private void unpack() throws InvalidFrameException {


        byte[] x = data;

        if (x == null) {
            throw new InvalidFrameException();
        }

        int L = x.length;
        if (L < 8) {
            log.warn("too short frame");
            throw new InvalidFrameException();
        }

        if (x[0] != ASTM.STX) {
            log.warn("some mandatory bytes are not there (STX at 0)");
            throw new InvalidFrameException();
        }
        if (x[L - 1] != ASTM.LF) {
            log.warn("some mandatory bytes are not there (LF at L-1)");
            throw new InvalidFrameException();
        }
        if (x[L - 2] != ASTM.CR) {
            log.warn("some mandatory bytes are not there (CR at L-2)");
            throw new InvalidFrameException();
        }


        if (x[L - 5] != ASTM.ETX && x[L - 5] != ASTM.ETB) {
            log.warn("some mandatory bytes are not there (ETX or ETB at L-5)");
            throw new InvalidFrameException();
        }

        byte[] r = new byte[L - 6];
        System.arraycopy(x, 1, r, 0, L - 6);

        byte[] y = ASTM.getChecksum(r, x[L - 5]);
        if (y[0] != x[L - 4] || y[1] != x[L - 3]) {
            log.warn("invalid checksum (expected " + ASTM.niceByteRepresentation(y));
            throw new InvalidFrameException();
        }
        byte frameSequence = r[0];

        if (ASTM.STRICT) {
            if (frameCounter != frameSequence) {
                log.warn("invalid frame is:" + ASTM.niceByteRepresentation(r));
                log.warn("invalid frame counter" + frameCounter + " vs " + frameSequence);
                throw new InvalidFrameException();
            }

        }
        if (x[L - 5] == ASTM.ETX) {


            if (x[L - 6] != ASTM.CR) {
                log.warn("some mandatory bytes are not there (CR at L-6)");
                throw new InvalidFrameException();
            }


            payloadBytes = new byte[r.length - 2];
            System.arraycopy(r, 1, payloadBytes, 0, r.length - 2);
            lastBlock = true;

        } else {

            payloadBytes = new byte[r.length - 1];
            System.arraycopy(r, 1, payloadBytes, 0, r.length - 1);
            lastBlock = false;
        }


    }

    public boolean isEOT() {
        return EOT;
    }

    private void setEOT() {
        this.EOT = true;
    }
}
