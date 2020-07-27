package com.cyq.progressview;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cyq.progressview.button.MyButton;
import com.cyq.progressview.test.TestActivity;
import com.cyq.progressview.widget.AnimNumberView;
import com.cyq.progressview.widget.ProgressLayout;

import java.util.Random;

/**
 * @author : ChenYangQi
 * date   : 2020/7/1 10:06
 * desc   : 全局的按钮控件，以下三类按钮的功能集合
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ProgressLayout mProgress;
    private boolean tagger = false;
    private FrameLayout mContainer;
    private LinearLayout mBtnContainer;
    private MyButton mBtnUpTemp;
    private MyButton mBtnDownTemp;
    private MyButton mBtnUpTimer;
    private MyButton mBtnDownTimer;
    private Button mBtnTest;
    private final int targetTemperature = 240;
    private Random mRandom = new Random();
    /**
     * 当前温度
     */
    float temperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContainer = findViewById(R.id.mContainer);
        mProgress = findViewById(R.id.mProgress);
        mBtnUpTemp = findViewById(R.id.btn_1);
        mBtnDownTemp = findViewById(R.id.btn_2);
        mBtnUpTimer = findViewById(R.id.btn_3);
        mBtnDownTimer = findViewById(R.id.btn_4);
        mBtnContainer = findViewById(R.id.mBtnContainer);
        mBtnTest = findViewById(R.id.mBtnTest);
        layoutAnim();
        mContainer.setOnClickListener(v -> {
            layoutAnim();
        });
        mBtnUpTemp.setOnClickListener(this);
        mBtnDownTemp.setOnClickListener(this);
        mBtnUpTimer.setOnClickListener(this);
        mBtnDownTimer.setOnClickListener(this);
        mBtnTest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_1:
                //预热
                temperature = 0;
                mHandler.removeCallbacksAndMessages(null);
                mHandler.sendEmptyMessageDelayed(101, 0);
                break;

            case R.id.btn_2:
                //清洁模式
                mHandler.removeCallbacksAndMessages(null);
                mProgress.setCleanMode(30, new ProgressLayout.OnCompleteListener() {
                    @Override
                    public void onComplete() {
                        Toast.makeText(MainActivity.this, "清洁完成", Toast.LENGTH_SHORT).show();
                    }
                });
                break;

            case R.id.btn_3:
                //正计时
                mProgress.setTimer(20, AnimNumberView.UP_TIMER);
                mProgress.setOnCompleteListener(new ProgressLayout.OnCompleteListener() {
                    @Override
                    public void onComplete() {
                        Toast.makeText(MainActivity.this, "正计时结束", Toast.LENGTH_SHORT).show();
                    }
                });
                break;

            case R.id.btn_4:
                //倒计时
                mProgress.setTimer(20, AnimNumberView.DOWN_TIMER);
                mProgress.setOnCompleteListener(new ProgressLayout.OnCompleteListener() {
                    @Override
                    public void onComplete() {
                        Toast.makeText(MainActivity.this, "倒计时结束", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.mBtnTest:
                startActivity(new Intent(MainActivity.this, TestActivity.class));
                break;
            default:
        }
    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 101:
                    if (mHandler == null) {
                        break;
                    }
                    temperature = temperature + 1 + mRandom.nextInt(30);
                    //temperature++;
                    if (temperature >= targetTemperature) {
                        temperature = targetTemperature;
                        mHandler.removeCallbacksAndMessages(null);
                    } else {
                        mHandler.sendEmptyMessageDelayed(101, 2000);
                    }
                    mProgress.setTemperature(temperature, targetTemperature);
                    break;
                default:
            }
            return false;
        }
    });

    /**
     * 触摸执行动画
     */
    private void layoutAnim() {
        if (tagger) {
            //按钮下移
            mBtnContainer.animate()
                    .translationY(700F)
                    .setDuration(150)
                    .withLayer()
                    .start();

            mBtnContainer.setVisibility(View.VISIBLE);
            mProgress.animate()
                    .translationY(0)
                    .setDuration(150)
                    .scaleX(1f)
                    .scaleY(1f)
                    .withLayer()
                    .start();
        } else {
            //按钮上移
            mBtnContainer.animate()
                    .translationY(0F)
                    .setDuration(150)
                    .withLayer()
                    .start();

            mProgress.animate()
                    .translationY(-300F)
                    .scaleX(0.9f)
                    .scaleY(0.9f)
                    .setDuration(150)
                    .withLayer()
                    .start();
        }
        tagger = !tagger;
    }
}
