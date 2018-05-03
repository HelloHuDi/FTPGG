package com.hd.ftpgg;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hd.ftplibrary.ftps.FsService;
import com.hd.ftplibrary.model.FTPControl;

public class SocketActivity extends BaseActivity {

    private Button button;

    private TextView tvLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);
        tvLog = findViewById(R.id.tvLog);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(FsService.ACTION_STARTED);
        intentFilter.addAction(FsService.ACTION_STOPPED);
        intentFilter.addAction(FsService.ACTION_FAILEDTOSTART);
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d("tag", "服务通知：" + action);
            if (action != null)
                switch (action) {
                    case FsService.ACTION_STARTED:
                        button.setText("stop socket");
                        button.setTag("1");
                        tvLog.append("服务开启成功,局域网下，使用浏览器访问下面地址：\n\n" + APP.getFsInfo().getIp() + "\n\n");
                        Log.d("tag", "服务开启成功 ：" + APP.getFsInfo().getIp() + "\n");
                        break;
                    case FsService.ACTION_STOPPED:
                        button.setText("start socket");
                        button.setTag("0");
                        tvLog.append("服务关闭\n\n");
                        break;
                    case FsService.ACTION_FAILEDTOSTART:
                        button.setText("start socket");
                        button.setTag("0");
                        tvLog.append("服务启动失败\n\n");
                        break;
                }
        }
    };

    public void controlSocket(View view) {
        button = (Button) view;
        int tag = Integer.valueOf((String) button.getTag());
        switch (tag) {
            case 0:
                FTPControl.startSocket(APP.getFsInfo());
                break;
            case 1:
                FTPControl.stopSocket(APP.getFsInfo());
                break;
        }
    }
}
