package com.ngnydevices.open.a9000p;



public class ASTM {


    public static final Integer ACK = 6;
    public static final Integer ENQ = 5;
    public static final Integer NAK = 0x15;
    public static final Integer EOT = 4;
    public static final int INITIAL_FRAME_NUMBER = 48;
    public static final int LAST_FRAME_NUMBER = 48 + 8;
    static final int CR = 13;
    static final int LF = 10;
    static final byte STX = 2;
    static final byte ETX = 3;
    static final byte ETB = 23;

    public static final int ASTM_DIVIDE_LIMIT  =240;


    final static char[] hexArray = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    public static final boolean STRICT = false;

    private static String byteToHex(byte bytesj) {
        char[] hexChars = new char[2];
        int v;
        v = bytesj & 0xFF;
        hexChars[0] = hexArray[v >>> 4];
        hexChars[1] = hexArray[v & 0x0F];
        return new String(hexChars);
    }


    public static String toAstmDisplay(byte b) {


        if (b == '>') return "&gt;";
        if (b == '<') return "&lt;";

        if (b >= 32) {


            return "" + (char) b;
        } else {
            String d = "<0x" + byteToHex(b) + ">";
            switch (b) {
                case 5:
                    d = "<ENQ>";
                    break;
                case 6:
                    d = "<ACK>";
                    break;
                case 2:
                    d = "<STX>";
                    break;
                case 10:
                    d = "<LF>";
                    break;
                case 13:
                    d = "<CR>";
                    break;
                case 3:
                    d = "<ETX>";
                    break;
                case 23:
                    d = "<ETB>";
                    break;
                case 4:
                    d = "<EOT>";
                    break;
                case 21:
                    d = "<NAK>";
                    break;
            }
            return d;
        }

    }



    public static String niceByteRepresentation(byte[] b) {
        StringBuilder line = new StringBuilder();
        if (b != null) {
            for (byte aB : b) {
                line.append(toAstmDisplay(aB));
            }
        }
        return line.toString();
    }



    static byte[] getChecksum(byte[] x, byte last) {

        int iChkSum = 0;
        for (byte aX : x) {
            iChkSum += aX;
        }
        iChkSum += last;
        iChkSum = (iChkSum & 0xFF);
        char iChkLo = hexArray[iChkSum % 16];
        char iChkHi = hexArray[iChkSum / 16];
        byte[] a = new byte[2];
        a[0] = (byte) (iChkHi);
        a[1] = (byte) (iChkLo);
        return a;

    }




}
