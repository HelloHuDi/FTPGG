package com.hd.ftplibrary.model;

import android.content.Intent;
import android.util.Log;

import com.hd.ftplibrary.ftpc.FTPClient;
import com.hd.ftplibrary.ftpc.FTPException;
import com.hd.ftplibrary.ftpc.FTPIllegalReplyException;
import com.hd.ftplibrary.ftps.FsService;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by hd on 2018/5/1 .
 * control ftp socket and client
 */
public class FTPControl {

    public static void start(FcInfo fcInfo, FsInfo fsInfo) {
        startClient(fcInfo);
        startSocket(fsInfo);
    }

    public static void stop(FcInfo fcInfo, FsInfo fsInfo) {
        stopClient(fcInfo);
        stopSocket(fsInfo);
    }

    public static void startClient(FcInfo fcInfo) {
        stopClient(fcInfo);
        FTPClient ftpClient = new FTPClient();
        try {
            String[] message = ftpClient.connect(fcInfo.getHost(), fcInfo.getPort());
            Log.d(FcInfo.FCINFO_TAG, "FTPClient connect message :" + Arrays.toString(message));
            fcInfo.setFtpClient(ftpClient);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FTPIllegalReplyException e) {
            e.printStackTrace();
        } catch (FTPException e) {
            e.printStackTrace();
        }
    }

    public static void stopClient(FcInfo fcInfo) {
        FTPClient ftpClient = fcInfo.getFtpClient();
        if (ftpClient != null && ftpClient.isConnected()) {
            try {
                ftpClient.disconnect(fcInfo.isSendQuitCommand());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (FTPIllegalReplyException e) {
                e.printStackTrace();
            } catch (FTPException e) {
                e.printStackTrace();
            }
            fcInfo.setFtpClient(null);
        }
    }

    public static void startSocket(FsInfo fsInfo) {
        fsInfo.getContext().sendBroadcast(setIntent(fsInfo, true));
    }

    public static void stopSocket(FsInfo fsInfo) {
        fsInfo.getContext().sendBroadcast(setIntent(fsInfo, false));
    }

    private static Intent setIntent(FsInfo fsInfo, boolean startService) {
        Intent intent = new Intent(startService ? FsService.ACTION_START_FTPSERVER : FsService.ACTION_STOP_FTPSERVER);
        intent.putExtra(FsInfo.FSINFO_TAG, fsInfo);
        return intent;
    }
}
