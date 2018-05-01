package com.hd.ftplibrary.model;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;

import com.hd.ftplibrary.ftps.FsService;

import java.io.File;

/**
 * Created by hd on 2018/5/1 .
 * ftp client info
 */
public class FsInfo extends FTPInfo {

    private String accountUserName, accountPassword;

    private boolean allowAnonymous,takeFullWakeLock;

    private int portNumber;

    private String chrootDirPath=defaultPath();

    private FsService fsService;

    private FsInfo() {
    }

    private FsInfo(@NonNull Context context) {
        setContext(context);
    }

    private String defaultPath() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        } else {
            return String.valueOf(new File("/"));
        }
    }

    public String getAccountUserName() {
        return accountUserName;
    }

    public void setAccountUserName(String accountUserName) {
        this.accountUserName = accountUserName;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public boolean isAllowAnonymous() {
        return allowAnonymous;
    }

    public void setAllowAnonymous(boolean allowAnonymous) {
        this.allowAnonymous = allowAnonymous;
    }

    public boolean isTakeFullWakeLock() {
        return takeFullWakeLock;
    }

    public void setTakeFullWakeLock(boolean takeFullWakeLock) {
        this.takeFullWakeLock = takeFullWakeLock;
    }

    public int getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }

    public String getChrootDirPath() {
        return chrootDirPath;
    }

    public void setChrootDirPath(String chrootDirPath) {
        this.chrootDirPath = chrootDirPath;
    }

    public FsService getFsService() {
        return fsService;
    }

    public void setFsService(FsService fsService) {
        this.fsService = fsService;
    }

    @Override
    void clear() {

    }

    public static class Builder {

        private FsInfo info;

        public Builder() {
            info = new FsInfo();
        }

        public Builder(@NonNull Context context) {
            info = new FsInfo(context);
        }

        public FsInfo.Builder setAccountUserName(String accountUserName) {
            info.setAccountUserName(accountUserName);
            return this;
        }

        public FsInfo.Builder setAccountPassword(String accountPassword) {
            info.setAccountPassword(accountPassword);
            return this;
        }

        public FsInfo.Builder setAllowAnonymous(boolean allowAnonymous) {
            info.setAllowAnonymous(allowAnonymous);
            return this;
        }

        public FsInfo.Builder setTakeFullWakeLock(boolean takeFullWakeLock) {
            info.setTakeFullWakeLock(takeFullWakeLock);
            return this;
        }

        public FsInfo.Builder setChrootDirPath(String chrootDirPath) {
            info.setChrootDirPath(chrootDirPath);
            return this;
        }

        public FsInfo.Builder setPortNumber(int portNumber) {
            info.setPortNumber(portNumber);
            return this;
        }

        public FsInfo build() {
            return info;
        }
    }

}
