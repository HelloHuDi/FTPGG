package com.hd.ftpgg;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hd.splashscreen.text.SimpleConfig;
import com.hd.splashscreen.text.SimpleSplashFinishCallback;
import com.hd.splashscreen.text.SimpleSplashScreen;

import java.util.Arrays;

public class SplashActivity extends BaseActivity implements SimpleSplashFinishCallback {

    private boolean permissionGranted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SimpleSplashScreen screen = findViewById(R.id.screen);
        SimpleConfig simpleConfig = new SimpleConfig(this);
        simpleConfig.setCallback(this);
        screen.addConfig(simpleConfig);
        screen.start();
    }

    @Override
    public void loadFinish() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (!isDestroyed()) {
                requestPermission();
            }
        } else {
            permissionGranted = true;
        }
    }

    final static int PERMISSIONS_REQUEST_CODE = 12;

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSIONS_REQUEST_CODE);
            }else{
                permissionGranted=true;
            }
        }else{
            permissionGranted = true;
        }
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
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permissions are rejected !", Toast.LENGTH_LONG).show();
                    permissionGranted = false;
                    return;
                }
            }
            permissionGranted = true;
        }
    }

    public void start(View view) {
        if (permissionGranted) {
            Class cls = null;
            switch (view.getId()) {
                case R.id.socket:
                    cls=SocketActivity.class;
                    break;
                case R.id.client:
                    cls=ClientActivity.class;
                    break;
            }
            startActivity(new Intent(this,cls));
        }else{
            Toast.makeText(this, "Permissions are rejected !", Toast.LENGTH_LONG).show();
        }
    }

}
