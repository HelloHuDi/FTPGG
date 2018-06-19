package com.hd.ftplibrary.model;

import android.content.Intent;
import android.support.annotation.NonNull;
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

    public static void start(@NonNull FcInfo fcInfo, @NonNull FsInfo fsInfo) {
        startClient(fcInfo);
        startSocket(fsInfo);
    }

    public static void stop(@NonNull FcInfo fcInfo, @NonNull FsInfo fsInfo) {
        stopClient(fcInfo);
        stopSocket(fsInfo);
    }

    public static void startClient(@NonNull FcInfo fcInfo) {
        stopClient(fcInfo);
        FTPClient ftpClient = new FTPClient();
        new Thread(() -> {
            try {
                String[] message = ftpClient.connect(fcInfo.getHost(), fcInfo.getPort());
                Log.d(FcInfo.FCINFO_TAG, "FTPClient connect message :" + Arrays.toString(message));
                fcInfo.setFtpClient(ftpClient);
                fcInfo.getCallback().startUp(true);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (FTPIllegalReplyException e) {
                e.printStackTrace();
            } catch (FTPException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            fcInfo.getCallback().startUp(false);
        }).start();
    }

    public static boolean stopClient(@NonNull FcInfo fcInfo) {
        FTPClient ftpClient = fcInfo.getFtpClient();
        if (ftpClient != null && ftpClient.isConnected()) {
            try {
                ftpClient.disconnect(fcInfo.isSendQuitCommand());
                fcInfo.setFtpClient(null);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (FTPIllegalReplyException e) {
                e.printStackTrace();
            } catch (FTPException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
        return true;
    }

    public static void startSocket(@NonNull FsInfo fsInfo) {
        fsInfo.getContext().sendBroadcast(setIntent(true));
    }

    public static void stopSocket(@NonNull FsInfo fsInfo) {
        fsInfo.getContext().sendBroadcast(setIntent(false));
    }

    private static Intent setIntent(boolean startService) {
        return new Intent(startService ? FsService.ACTION_START_FTPSERVER : FsService.ACTION_STOP_FTPSERVER);
    }
}
