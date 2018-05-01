package com.hd.ftplibrary.model;

import android.content.Context;
import android.util.Log;

import com.hd.ftplibrary.ftps.FsService;
import com.hd.ftplibrary.ftps.FsSettings;
import com.hd.ftplibrary.util.PreferenceUtils;

/**
 * Created by hd on 2018/5/1 .
 * ftp client info
 */
public class FsInfo extends FTPInfo {

    private String accountUserName = defaultUserNamePassword, accountPassword = defaultUserNamePassword;

    private boolean allowAnonymous = false, takeFullWakeLock = false;

    private int portNumber = 2121;

    private String chrootDirPath;

    private FsService fsService;

    private FsInfo() {
        this(null);
    }

    private FsInfo(Context context) {
        setContext(context);
    }

    public String getAccountUserName() {
        return accountUserName;
    }

    public void setAccountUserName(String accountUserName) {
        this.accountUserName = accountUserName;
        PreferenceUtils.put(getContext(), "username", accountUserName);
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
        PreferenceUtils.put(getContext(), "password", accountPassword);
    }

    public boolean isAllowAnonymous() {
        return allowAnonymous;
    }

    public void setAllowAnonymous(boolean allowAnonymous) {
        this.allowAnonymous = allowAnonymous;
        PreferenceUtils.put(getContext(), "allow_anonymous", allowAnonymous);
    }

    public boolean isTakeFullWakeLock() {
        return takeFullWakeLock;
    }

    public void setTakeFullWakeLock(boolean takeFullWakeLock) {
        this.takeFullWakeLock = takeFullWakeLock;
        PreferenceUtils.put(getContext(), "stayAwake", takeFullWakeLock);
    }

    public int getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
        PreferenceUtils.put(getContext(), "portNum", String.valueOf(portNumber));
    }

    public String getChrootDirPath() {
        return chrootDirPath;
    }

    public void setChrootDirPath(String chrootDirPath) {
        this.chrootDirPath = chrootDirPath;
        boolean status=FsSettings.setChrootDir(chrootDirPath);
        Log.d("tag","set chrootDir path status :"+status);
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
            this(null);
        }

        public Builder(Context context) {
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
