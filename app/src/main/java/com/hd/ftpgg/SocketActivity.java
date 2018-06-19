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
import android.widget.EditText;
import android.widget.TextView;

import com.hd.ftplibrary.ftps.FsService;
import com.hd.ftplibrary.model.FTPControl;

public class SocketActivity extends BaseActivity {

    private Button button;

    private TextView tvLog;

    private EditText etPort,etAccountUserName,etAccountPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);
        initView();
        initData();
    }

    private void initData() {
        etPort.setText(String.valueOf(APP.getFsInfo().getPortNumber()));
        etAccountUserName.setText(APP.getFsInfo().getAccountUserName());
        etAccountPassword.setText(APP.getFsInfo().getAccountPassword());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(FsService.ACTION_STARTED);
        intentFilter.addAction(FsService.ACTION_STOPPED);
        intentFilter.addAction(FsService.ACTION_FAILEDTOSTART);
        registerReceiver(broadcastReceiver, intentFilter);
    }

    private void initView() {
        tvLog = findViewById(R.id.tvLog);
        etPort = findViewById(R.id.etPort);
        etAccountUserName = findViewById(R.id.etAccountUserName);
        etAccountPassword = findViewById(R.id.etAccountPassword);
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
                        updateButton("stop socket", "1");
                        updateLog("服务开启成功,局域网下，使用浏览器访问下面地址：\n\n" + APP.getFsInfo().getIp() + "\n\n");
                        break;
                    case FsService.ACTION_STOPPED:
                        updateButton("start socket", "0");
                        updateLog("服务关闭\n\n");
                        break;
                    case FsService.ACTION_FAILEDTOSTART:
                        updateButton("start socket", "0");
                        updateLog("服务启动失败\n\n");
                        break;
                }
        }
    };

    private void updateLog(String text) {
        tvLog.append(text);
    }

    private void updateButton(String s, String s2) {
        button.setText(s);
        button.setTag(s2);
    }

    public void controlSocket(View view) {
        button = (Button) view;
        int tag = Integer.valueOf((String) button.getTag());
        switch (tag) {
            case 0:
                updateFsInfo();
                FTPControl.startSocket(APP.getFsInfo());
                break;
            case 1:
                FTPControl.stopSocket(APP.getFsInfo());
                break;
        }
    }

    private void updateFsInfo() {
        String portStr = String.valueOf(etPort.getText());
        int port = (portStr != null && portStr.length() > 0) ? Integer.valueOf(portStr) : 2121;
        APP.getFsInfo().setPortNumber(port);
        APP.getFsInfo().setAccountUserName(etAccountUserName.getText().toString());
        APP.getFsInfo().setAccountPassword(etAccountPassword.getText().toString());
    }
}
