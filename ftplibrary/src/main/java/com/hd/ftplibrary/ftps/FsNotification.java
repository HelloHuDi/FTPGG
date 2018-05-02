/*
Copyright 2018 HelloHuDi

Copyright 2011-2013 Pieter Pareit

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

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class FsNotification extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("tag", "onReceive broadcast: " + intent.getAction());
      /*  String action = intent.getAction();
        if (action != null)
            switch (action) {
                case FsService.ACTION_STARTED:
                    String text;
                    InetAddress address = FsService.getLocalInetAddress();
                    if (address == null) {
                        text = "Unable to retrieve wifi ip address";
                    } else {
                        text = "ftp://" + address.getHostAddress() + ":" + FsSettings.getPortNumber() + "/";
                    }
                    Log.d("tag","ftp ip : "+ text);
                    break;
                case FsService.ACTION_STOPPED:
                    break;
            }*/
    }
}
