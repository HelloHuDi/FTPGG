package com.hd.ftpgg;

import android.app.Application;

import com.hd.ftplibrary.ftpc.FTPClient;
import com.hd.ftplibrary.ftps.FsService;
import com.hd.ftplibrary.model.FTPApp;
import com.hd.ftplibrary.model.FcInfo;
import com.hd.ftplibrary.model.FsInfo;


/**
 * Created by hd on 2018/5/1 .
 */
public class APP extends Application {

    private FsInfo fsInfo;

    private FcInfo fcInfo;

    private FTPClient ftpClient;

    private FsService fsService;

    public FsInfo getFsInfo() {
        return fsInfo;
    }

    public FcInfo getFcInfo() {
        return fcInfo;
    }

    public FTPClient getFtpClient() {
        return fcInfo.getFtpClient();
    }

    public FsService getFsService() {
        return fsInfo.getFsService();
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
                  .setPortNumber(2121)//
                  .build();
        fcInfo = new FcInfo.Builder()//
                  .setLoginUserName("ftp")//
                  .setLoginPassword("ftp")//
                  .build();
    }
}
