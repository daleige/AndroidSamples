package com.cyq.download;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.tbruyelle.rxpermissions2.RxPermissions;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ProgressBar mProgress;
    private TextView mPresent;
    private Button mBtnDownload;
    private Button mBtnCancel;
    private RxPermissions rxPermissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initPermissions();
    }

    private void initView() {
        mProgress = findViewById(R.id.progress);
        mPresent = findViewById(R.id.tv_progress);
        mBtnCancel = findViewById(R.id.btn_cancel);
        mBtnDownload = findViewById(R.id.btn_download);
        mBtnDownload.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);
    }

    /**
     * 申请文件读写权限
     */
    private void initPermissions() {
        rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_download:

                break;
            case R.id.btn_cancel:

                break;
            default:
        }
    }
}
