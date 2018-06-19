package com.hd.ftpgg;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hd.ftplibrary.callback.FTPClientCallback;
import com.hd.ftplibrary.model.FTPControl;

public class ClientActivity extends BaseActivity implements FTPClientCallback {

    private Button button;

    private TextView tvLog;

    private EditText etHost, etPort, etUserName, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        initView();
        initData();
    }

    private void initData() {
        etHost.setText(APP.getFcInfo().getHost());
        etPort.setText(String.valueOf(APP.getFcInfo().getPort()));
        etUserName.setText(APP.getFcInfo().getLoginUserName());
        etPassword.setText(APP.getFcInfo().getLoginPassword());
    }

    private void initView() {
        tvLog = findViewById(R.id.tvLog);
        etHost = findViewById(R.id.etHost);
        etPort = findViewById(R.id.etPort);
        etUserName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);
    }

    public void controlClient(View view) {
        button = (Button) view;
        int tag = Integer.valueOf((String) button.getTag());
        switch (tag) {
            case 0:
                startClient();
                break;
            case 1:
                stopClient();
                break;
        }
    }

    @SuppressLint("SetTextI18n")
    private void startClient() {
        updateFcInfo();
        APP.getFcInfo().setCallback(this);
        FTPControl.startClient(APP.getFcInfo());
    }

    @Override
    public void startUp(boolean success) {
        if (success) {
            updateButton("stop client", "1");
            updateLog("ftp服务连接成功 \n\n");
        } else {
            updateButton("start client", "0");
            updateLog("ftp服务连接失败 \n\n");
        }
    }

    @SuppressLint("SetTextI18n")
    private void stopClient() {
        boolean success = FTPControl.stopClient(APP.getFcInfo());
        if (success) {
            updateButton("start client", "1");
            updateLog("ftp服务断开连接成功 \n\n");
        } else {
            updateButton("stop client", "0");
            updateLog("ftp服务断开连接失败 \n\n");
        }
    }

    private void updateLog(String text) {
        tvLog.setText(text);
    }

    private void updateButton(String s, String s2) {
        button.setText(s);
        button.setTag(s2);
    }

    private void updateFcInfo() {
        APP.getFcInfo().setHost(etHost.getText().toString());
        String portStr = String.valueOf(etPort.getText());
        int port = (portStr != null && portStr.length() > 0) ? Integer.valueOf(portStr) : 2121;
        APP.getFcInfo().setPort(port);
        APP.getFcInfo().setLoginUserName(etUserName.getText().toString());
        APP.getFcInfo().setLoginUserName(etPassword.getText().toString());
    }
}
