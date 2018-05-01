package com.hd.ftplibrary.model;

import android.content.Context;

import com.hd.ftplibrary.ftpc.FTPClient;

/**
 * Created by hd on 2018/5/1 .
 * ftp socket info
 */
public class FcInfo extends FTPInfo {

    private String loginUserName = defaultUserNamePassword, loginPassword = defaultUserNamePassword;

    private FTPClient ftpClient;

    private FcInfo() {
        this(null);
    }

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

    public FTPClient getFtpClient() {
        return ftpClient;
    }

    public void setFtpClient(FTPClient ftpClient) {
        this.ftpClient = ftpClient;
    }

    @Override
    void clear() {

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

        public FcInfo build() {
            return info;
        }
    }

}
