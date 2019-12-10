package com.cyq.breadscaleview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.cyq.library.BreadScaleView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BreadScaleView scaleView = findViewById(R.id.bv_test);

    }
}
