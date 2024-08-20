package com.ngnydevices.open.a9000p;


class ByteFrame {
    private final byte[] bytePayload;
    private final int subframeIndex;
    private final int subframeCount;
    private Frame originalFrame;
    private boolean last;

    public ByteFrame(byte[] bytePayload, int subframeIndex, int subframeCount) {
        this.bytePayload = bytePayload;
        this.subframeIndex = subframeIndex;
        this.subframeCount = subframeCount;
    }

    public byte[] getBytePayload() {
        return bytePayload;
    }


    public int getSubframeIndex() {
        return subframeIndex;
    }

    public int getSubframeCount() {
        return subframeCount;
    }

    public Frame getOriginalFrame() {
        return originalFrame;
    }

    public void setOriginalFrame(Frame originalFrame) {
        this.originalFrame = originalFrame;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

}
