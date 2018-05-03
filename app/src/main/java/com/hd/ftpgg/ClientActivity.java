package com.hd.ftpgg;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hd.ftplibrary.model.FTPControl;

public class ClientActivity extends BaseActivity {

    private Button button;

    private TextView tvLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        tvLog = findViewById(R.id.tvLog);
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
        boolean success=FTPControl.startClient(APP.getFcInfo());
        if(success){
            button.setText("stop client");
            button.setTag("1");
            tvLog.append("ftp服务连接成功 \n\n");
        }else{
            button.setText("start client");
            button.setTag("0");
            tvLog.append("ftp服务连接失败 \n\n");
        }
    }

    @SuppressLint("SetTextI18n")
    private void stopClient() {
        boolean success= FTPControl.stopClient(APP.getFcInfo());
        if(success){
            button.setText("start client");
            button.setTag("1");
            tvLog.append("ftp服务断开连接成功 \n\n");
        }else{
            button.setText("stop client");
            button.setTag("0");
            tvLog.append("ftp服务断开连接失败 \n\n");
        }
    }
}
