package com.cyq.customview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private BigImageView mBigIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBigIv=findViewById(R.id.iv_big);
        try {
            InputStream inputStream = this.getAssets().open("bbb.jpg");
            mBigIv.setImage(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
