package com.hd.ftplibrary.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.hd.ftplibrary.ftps.FsService;
import com.hd.ftplibrary.ftps.FsSettings;
import com.hd.ftplibrary.util.PreferenceUtils;

import java.net.InetAddress;

/**
 * Created by hd on 2018/5/1 .
 * ftp client info
 */
@SuppressLint("ParcelCreator")
public class FsInfo extends FTPInfo {

    public final static String FSINFO_TAG = "fsInfo";

    private String accountUserName = defaultUserNamePassword, accountPassword = defaultUserNamePassword;

    private boolean allowAnonymous = false, takeFullWakeLock = false;

    private int portNumber;

    private String chrootDirPath;

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
        boolean status = FsSettings.setChrootDir(chrootDirPath);
        Log.d("tag", "set chrootDir path status :" + status);
    }

    public boolean isRunning() {
        return FsService.isRunning();
    }

    public String getIp() {
        if (isRunning()) {
            InetAddress address = FsService.getLocalInetAddress();
            if (address == null) {
                Log.e("tag", "Unable to retrieve wifi ip address");
                return "";
            }
            return "ftp://" + address.getHostAddress() + ":" + FsSettings.getPortNumber() + "/";
        }
        return "";
    }

    @Override
    void clear() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Bundle bundle=new Bundle();
        bundle.putString("accountUserName",accountUserName);
        bundle.putString("accountPassword",accountPassword);
        bundle.putString("chrootDirPath",chrootDirPath);
        bundle.putInt("portNumber",portNumber);
        bundle.putBoolean("allowAnonymous",allowAnonymous);
        bundle.putBoolean("takeFullWakeLock",takeFullWakeLock);
        dest.writeBundle(bundle);
    }

    public static final Parcelable.Creator<FsInfo> CREATOR = new Creator<FsInfo>() {
        @Override
        public FsInfo createFromParcel(Parcel source) {
            FsInfo fsInfo=new FsInfo(null);
            Bundle bundle=source.readBundle(getClass().getClassLoader());
            fsInfo.setAccountUserName(bundle.getString("accountUserName"));
            fsInfo.setAccountPassword( bundle.getString("accountPassword"));
            fsInfo.setChrootDirPath(bundle.getString("chrootDirPath"));
            fsInfo.setPortNumber(bundle.getInt("portNumber"));
            fsInfo.setAllowAnonymous(bundle.getBoolean("allowAnonymous"));
            fsInfo.setTakeFullWakeLock(bundle.getBoolean("takeFullWakeLock"));
            return fsInfo;
        }

        @Override
        public FsInfo[] newArray(int size) {
            return new FsInfo[0];
        }
    };


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
