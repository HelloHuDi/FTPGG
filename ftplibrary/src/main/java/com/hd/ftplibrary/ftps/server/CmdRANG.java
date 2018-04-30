package com.hd.ftplibrary.ftps.server;


import android.util.Log;

public class CmdRANG extends FtpCmd implements Runnable {

    private String input;

    public CmdRANG(SessionThread sessionThread, String input) {
        super(sessionThread);
        this.input = input;
    }

    @Override
    public void run() {
        Log.d("tag","RANG executing");
        String param = getParameter(input);
        String splits[] = param.split(" ");
        String errString = null;

        mainblock:
        {
            if (splits.length != 2) {
                errString = "500 Malformed RANG command\r\n";
                break mainblock;
            } else if (!sessionThread.isBinaryMode()) {
                errString = "551 Server is not in binary mode\r\n";
                break mainblock;
            }

            long startPosition = 0L, endPosition = 0L;
            try {
                startPosition = Long.parseLong(splits[0]);
                endPosition = Long.parseLong(splits[1]);
            } catch (NumberFormatException e) {
                errString = "500 Invalid start and end position\r\n";
                break mainblock;
            }

            if (startPosition == 1 && endPosition == 0) {
                sessionThread.writeString("350 Resetting start and end positions\r\n");
                sessionThread.offset = -1L; // reset start position
                sessionThread.endPosition = -1L; //reset end position
                break mainblock;
            } else if (startPosition > endPosition) {
                errString = "500 Invalid start and end position\r\n";
                break mainblock;
            }

            sessionThread.offset = startPosition;
            sessionThread.endPosition = endPosition;
            sessionThread.writeString("350 Restarting at "
                    + startPosition + ". End Byte range at " + endPosition + ".\r\n");
        }

        if (errString != null) {
            sessionThread.writeString(errString);
        }
    }
}
