package com.hd.ftplibrary.model;

import android.annotation.SuppressLint;
import android.content.Context;

/**
 * Created by hd on 2018/5/1 .
 */
public class FTPApp {

    @SuppressLint("StaticFieldLeak")
    private static Context context;

    public static void setAppContext(Context context){
        FTPApp.context=context.getApplicationContext();
    }

    public static Context getAppContext(){
        return context;
    }
}
