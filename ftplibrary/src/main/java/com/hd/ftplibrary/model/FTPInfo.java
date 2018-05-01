package com.hd.ftplibrary.model;

import android.content.Context;
import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by hd on 2018/5/1 .
 * ftp info
 */
public abstract class FTPInfo implements Serializable {

    private Context context;

    abstract void clear();

    protected Context getContext() {
        if (context == null)
            context = FTPApp.getAppContext();
        return context;
    }

    void setContext(@NonNull Context context) {
        this.context = context;
    }

}
