package com.cyq.tbs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.cyq.tbs.view.FileReaderView;

public class CustomActivity extends AppCompatActivity {
    private FileReaderView fileReaderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        fileReaderView = findViewById(R.id.frv_test);
        fileReaderView.openFile("/sdcard/安卓开发规范.pdf");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fileReaderView.destroy();
    }
}
