package com.ngnydevices.open.a9000p;


import java.io.IOException;


abstract class GenericDataLinkLayer {



    public abstract void writeByte(byte b) throws Exception;

    public abstract void writeBytes(byte[] bytesToSend) throws IOException;

    public abstract Integer readByte() throws IOException;


    public abstract void setHost(String host);



    public abstract void close();

    public abstract byte[] readBytes(byte etx) throws IOException;

    public abstract void longTimeout();

    public abstract void shortTimeout();
}
