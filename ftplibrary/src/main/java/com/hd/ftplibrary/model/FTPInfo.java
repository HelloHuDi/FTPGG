package com.hd.ftplibrary.model;

import android.content.Context;

import java.io.Serializable;

/**
 * Created by hd on 2018/5/1 .
 * ftp info
 */
public abstract class FTPInfo implements Serializable {

    private Context context;

    String defaultUserNamePassword = "ftp";

    abstract void clear();

    protected Context getContext() {
        checkContext();
        return context;
    }

    void setContext(Context context) {
        this.context = context;
        checkContext();
    }

    private void checkContext() {
        if (context == null) {
            if (FTPApp.getAppContext() == null) {
                throw new NullPointerException("must init the ftp applicationContext first ");
            }else {
                context = FTPApp.getAppContext();
            }
        } else if (FTPApp.getAppContext() == null) {
            FTPApp.setAppContext(context);
        }
    }


}
