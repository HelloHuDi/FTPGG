package com.hd.ftplibrary.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Parcel;

import com.hd.ftplibrary.ftpc.FTPClient;

/**
 * Created by hd on 2018/5/1 .
 * ftp socket info
 */
@SuppressLint("ParcelCreator")
public class FcInfo extends FTPInfo {

    public final static String FCINFO_TAG = "fcInfo";

    private String loginUserName = defaultUserNamePassword;

    private String loginPassword = defaultUserNamePassword;

    private String host;

    private int port;

    private boolean sendQuitCommand;

    private FTPClient ftpClient;

    private FcInfo(Context context) {
        setContext(context);
    }

    public String getLoginUserName() {
        return loginUserName;
    }

    public void setLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isSendQuitCommand() {
        return sendQuitCommand;
    }

    public void setSendQuitCommand(boolean sendQuitCommand) {
        this.sendQuitCommand = sendQuitCommand;
    }

    public FTPClient getFtpClient() {
        return ftpClient;
    }

    public void setFtpClient(FTPClient ftpClient) {
        this.ftpClient = ftpClient;
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

    }

    public static class Builder {

        private FcInfo info;

        public Builder() {
            this(null);
        }

        public Builder(Context context) {
            info = new FcInfo(context);
        }

        public FcInfo.Builder setLoginUserName(String loginUserName) {
            info.setLoginUserName(loginUserName);
            return this;
        }

        public FcInfo.Builder setLoginPassword(String loginPassword) {
            info.setLoginPassword(loginPassword);
            return this;
        }

        public FcInfo.Builder setHost(String host) {
            info.setHost(host);
            return this;
        }

        public FcInfo.Builder setPort(int port) {
            info.setPort(port);
            return this;
        }

        public FcInfo.Builder setSendQuitCommand(boolean sendQuitCommand) {
            info.setSendQuitCommand(sendQuitCommand);
            return this;
        }

        public FcInfo build() {
            return info;
        }
    }

}
