package com.ngnydevices.open.a9000p;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BasicLogger {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
    private static final Object lock = new Object();
    public static final boolean showInsecure = false;
    private static final boolean showError = true;
    private static final boolean showWarning = true;
    private static final boolean showInfo = true;
    private static final boolean showVerbose = true;




    private String className;


    public BasicLogger(String className) {

        this.className = className;
    }



    public static String getTimeStamp() {


        return new SimpleDateFormat("yyyyMMdd HHmmss").format(new Date());

    }

    public static void out(String txt) {
        System.out.println("#INFO:" + txt);
    }

    public static void warning(String txt) {
        System.out.println("#WARN:" + txt);
        new Exception().printStackTrace();

    }





    public void info(String message) {

        print("I", message, null);
    }

    private void print(String i, String s, Throwable th) {


        try {

            if (i.equalsIgnoreCase("!") && !showInsecure) return;
            if (i.equalsIgnoreCase("V") && !showVerbose) return;
            if (i.equalsIgnoreCase("D") && !showVerbose) return;
            if (i.equalsIgnoreCase("I") && !showInfo) return;
            if (i.equalsIgnoreCase("W") && !showWarning) return;
            if (i.equalsIgnoreCase("E") && !showError) return;

            String cap = className;
            String line = "";

            synchronized (lock) {
                String message = i + line + " " + DATE_FORMAT.format(new Date()) + " " + limit(cap) + "  " + s;
                System.out.println(message);
                System.out.flush();

                if (th != null) {
                    StringWriter errors = new StringWriter();
                    th.printStackTrace(new PrintWriter(errors));
                    System.out.println(errors);
                }
                System.err.flush();

            }




        } catch (Throwable ignored) {
        }

    }

    private String limit(String s) {
        if (s.length() < 40) {
            StringBuilder sBuilder = new StringBuilder(s);
            while (sBuilder.length() < 40) sBuilder.append(" ");
            s = sBuilder.toString();
            return s;
        }


        return max(s);
    }

    private String max(String s) {
        if (s.length() > 40) {
            return "..." + s.substring(s.length() - (40 - 3));
        } else {
            return s;
        }

    }

    public void error(String s, Exception e) {
        print("E", s, e);


    }



    public void warn(String s) {
        print("W", s, null);
    }

    public void debug(String message) {

        print("D", message, null);
    }

    public void warn(String message, Throwable e) {
        print("W", message, e);
    }

    public void error(String message, Throwable ex) {
        print("E", message, ex);

    }


    public void error(String message) {
        print("E", message, null);
    }

    public BasicLogger setClassName(String className) {
        this.className = className;
        return this;
    }

    public void e(String message) {
        error(message);
    }

    public void i(String message) {
        info(message);
    }

    public void verbose(String message) {
        print("V", message, null);

    }

    public void error(Throwable th) {
        error(th.getMessage(), th);
    }

    public void insecure(String s) {
        if (showInsecure) {
            print("!", "INSECURE ============> " + s.replace("\n", "").replace("\r", ""), null);
        }
    }

    public void a(String message) {
        print("A", message, null);

    }


}
