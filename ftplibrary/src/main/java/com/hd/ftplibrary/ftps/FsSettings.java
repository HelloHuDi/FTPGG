/*
Copyright 2018 HelloHuDi
Copyright 2011-2013 Pieter Pareit
Copyright 2009 David Revell

This file is part of SwiFTP.

SwiFTP is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

SwiFTP is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with SwiFTP.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.hd.ftplibrary.ftps;

import android.os.Environment;
import android.util.Log;

import com.hd.ftplibrary.model.FTPApp;
import com.hd.ftplibrary.util.PreferenceUtils;

import java.io.File;

public class FsSettings {

    private final static String TAG = FsSettings.class.getSimpleName();

    public static String getUserName() {
       return (String) PreferenceUtils.get(FTPApp.getAppContext(), "username", "ftp");
    }

    public static String getPassWord() {
       return (String) PreferenceUtils.get(FTPApp.getAppContext(), "password", "ftp");
    }

    public static boolean allowAnoymous() {
        return (boolean) PreferenceUtils.get(FTPApp.getAppContext(), "allow_anonymous", false);
    }

    public static File getChrootDir() {
        String dirName = (String) PreferenceUtils.get(FTPApp.getAppContext(), "chrootDir", "");
        File chrootDir = new File(dirName);
        // when the stored dirName was not initialized, initialize to good default
        // or when the chrootDir is garbage, initialize to good default
        if (dirName.equals("") || !chrootDir.isDirectory()) {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                chrootDir = Environment.getExternalStorageDirectory();
            } else {
                chrootDir = new File("/");
            }
        }
        if (!chrootDir.isDirectory()) {
            Log.e(TAG, "getChrootDir: not a directory");
            // if this happens, we are screwed
            // we give it the application directory
            // but this will probably not be what the user wants
            return FTPApp.getAppContext().getFilesDir();
        }
        return chrootDir;
    }

    public static String getChrootDirAsString() {
        File dirFile = getChrootDir();
        return dirFile != null ? dirFile.getAbsolutePath() : "";
    }

    public static boolean setChrootDir(String dir) {
        File chrootTest = new File(dir);
        if (!chrootTest.isDirectory() || !chrootTest.canRead())
                return false;
        PreferenceUtils.put(FTPApp.getAppContext(),"chrootDir", dir);
        return true;
    }

    public static int getPortNumber() {
        // TODO: port is always an number, so store this accordenly
        String portString = (String) PreferenceUtils.get(FTPApp.getAppContext(), "portNum", "2121");
        int port = Integer.valueOf(portString);
        Log.v(TAG, "Using port: " + port);
        return port;
    }

    public static boolean shouldTakeFullWakeLock() {
        return (boolean) PreferenceUtils.get(FTPApp.getAppContext(), "stayAwake", false);
    }

    // cleaning up after his
    protected static int inputBufferSize = 256;
    protected static boolean allowOverwrite = false;
    protected static int dataChunkSize = 8192; // do file I/O in 8k chunks
    protected static int sessionMonitorScrollBack = 10;
    protected static int serverLogScrollBack = 10;

    public static int getInputBufferSize() {
        return inputBufferSize;
    }

    public static void setInputBufferSize(int inputBufferSize) {
        FsSettings.inputBufferSize = inputBufferSize;
    }

    public static boolean isAllowOverwrite() {
        return allowOverwrite;
    }

    public static void setAllowOverwrite(boolean allowOverwrite) {
        FsSettings.allowOverwrite = allowOverwrite;
    }

    public static int getDataChunkSize() {
        return dataChunkSize;
    }

    public static void setDataChunkSize(int dataChunkSize) {
        FsSettings.dataChunkSize = dataChunkSize;
    }

    public static int getSessionMonitorScrollBack() {
        return sessionMonitorScrollBack;
    }

    public static void setSessionMonitorScrollBack(int sessionMonitorScrollBack) {
        FsSettings.sessionMonitorScrollBack = sessionMonitorScrollBack;
    }

    public static int getServerLogScrollBack() {
        return serverLogScrollBack;
    }

    public static void setLogScrollBack(int serverLogScrollBack) {
        FsSettings.serverLogScrollBack = serverLogScrollBack;
    }

}
