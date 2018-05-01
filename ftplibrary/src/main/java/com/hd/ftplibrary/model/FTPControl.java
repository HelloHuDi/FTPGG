package com.hd.ftplibrary.model;

/**
 * Created by hd on 2018/5/1 .
 * control ftp socket and client
 */
public class FTPControl {

    public static void start(FcInfo fcInfo,FsInfo fsInfo) {
        startClient(fcInfo);
        startSocket(fsInfo);
    }

    public static void stop(FcInfo fcInfo,FsInfo fsInfo) {
        stopClient(fcInfo);
        stopSocket(fsInfo);
    }

    public static void startClient(FcInfo fcInfo) {

    }

    public static void stopClient(FcInfo fcInfo) {

    }

    public static void startSocket(FsInfo fsInfo) {
    }

    public static void stopSocket(FsInfo fsInfo) {
    }
}
