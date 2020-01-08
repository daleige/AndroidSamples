package com.cyq.customview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.cyq.customview.view.RoundImageView;

public class MainActivity extends AppCompatActivity {
    private RoundImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
