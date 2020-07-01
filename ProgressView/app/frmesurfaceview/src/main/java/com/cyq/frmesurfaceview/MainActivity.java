package com.cyq.frmesurfaceview;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements PictureInterface {

    private final String TAG = MainActivity.class.getSimpleName();
    private FrameAnimation mFrameAnimation;
    private Button mSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }


    private void initView() {
        mFrameAnimation = (FrameAnimation) findViewById(R.id.frame_animation);
        mSecond = (Button) findViewById(R.id.btn_second);
        mSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        initAnimation();
    }

    private void initAnimation() {
        //设置资源文件
        mFrameAnimation.setBitmapResoursID(srcId);
        //设置监听事件
        mFrameAnimation.setOnFrameFinisedListener(new FrameAnimation.OnFrameFinishedListener() {
            @Override
            public void onStop() {
                Log.e(TAG, "stop");

            }

            @Override
            public void onStart() {
                Log.e(TAG, "start");
                Log.e(TAG, Runtime.getRuntime().totalMemory() / 1024 + "k");
            }
        });

        //设置单张图片展示时长
        mFrameAnimation.setGapTime(10);
        mFrameAnimation.start();
    }
}

