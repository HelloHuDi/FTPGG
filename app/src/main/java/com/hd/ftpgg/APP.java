package com.hd.ftpgg;

import android.app.Application;

import com.hd.ftplibrary.ftpc.FTPClient;
import com.hd.ftplibrary.model.FTPApp;
import com.hd.ftplibrary.model.FcInfo;
import com.hd.ftplibrary.model.FsInfo;


/**
 * Created by hd on 2018/5/1 .
 */
public class APP extends Application {

    private static FsInfo fsInfo;

    private static FcInfo fcInfo;

    private static FTPClient ftpClient;

    public static FsInfo getFsInfo() {
        return fsInfo;
    }

    public static FcInfo getFcInfo() {
        return fcInfo;
    }

    public static FTPClient getFtpClient() {
        return fcInfo.getFtpClient();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FTPApp.setAppContext(this);
        fsInfo = new FsInfo.Builder()//
                                     .setAccountUserName("ftp")//
                                     .setAccountPassword("ftp")//
                                     .setAllowAnonymous(false)//
                                     .setTakeFullWakeLock(true)//
                                     .setChrootDirPath("")//
                                     .setPortNumber(3535)//
                                     .build();
        fcInfo = new FcInfo.Builder()//
                                     .setLoginUserName("ftp")//
                                     .setLoginPassword("ftp")//
                                     .setPort(3535)//
                                     .build();
    }
}
