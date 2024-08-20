package com.ngnydevices.open.a9000p;

public class TimeHelper {
    public static long now() {
        return System.currentTimeMillis();
    }

    public static void sleep(int i) throws InterruptedException {
        Thread.sleep(i);
    }
}
