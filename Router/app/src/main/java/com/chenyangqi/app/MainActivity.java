package com.chenyangqi.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.chenyangqi.router.annotations.Destination;

@Destination(url = "router://home_page",description = "首页")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}