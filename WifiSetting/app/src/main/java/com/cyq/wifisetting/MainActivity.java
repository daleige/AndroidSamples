package com.cyq.wifisetting;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.tbruyelle.rxpermissions2.RxPermissions;

/**
 * @author ChenYangQi
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermissions();
    }

    /**
     * 获取权限
     */
    private void requestPermissions() {
        RxPermissions rxPermissions = new RxPermissions(this);
//        rxPermissions.request(Manifest.)
    }
}
