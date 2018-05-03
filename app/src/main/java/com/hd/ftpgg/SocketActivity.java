package com.hd.ftpgg;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hd.ftplibrary.ftps.FsService;
import com.hd.ftplibrary.model.FTPControl;

import java.util.Arrays;

public class SocketActivity extends AppCompatActivity {

    private Button button;

    private TextView ip;

    final static int PERMISSIONS_REQUEST_CODE = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);
        ip = findViewById(R.id.ip);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSIONS_REQUEST_CODE);
            }
        }

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(FsService.ACTION_STARTED);
        intentFilter.addAction(FsService.ACTION_STOPPED);
        registerReceiver(new BroadcastReceiver() {
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
                            ip.append("服务开启成功：" + APP.getFsInfo().getIp() + "\n");
                            Log.d("tag", "服务开启成功：" + APP.getFsInfo().getIp() + "\n");
                            break;
                        case FsService.ACTION_STOPPED:
                            button.setText("start socket");
                            button.setTag("0");
                            ip.append("服务关闭\n");
                            break;
                    }
            }
        }, intentFilter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode != PERMISSIONS_REQUEST_CODE) {
            Log.e("tag", "Unhandled request code");
            return;
        }
        Log.d("tag", "permissions: " + Arrays.toString(permissions));
        Log.d("tag", "grantResults: " + Arrays.toString(grantResults));
        if (grantResults.length > 0) {
            // Permissions not granted, close down
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "权限被拒绝", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        }
    }

    public void controlServer(View view) {
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
