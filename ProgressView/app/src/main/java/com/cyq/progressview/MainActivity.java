package com.cyq.progressview;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cyq.progressview.button.MyButton;
import com.cyq.progressview.widget.ProgressLayout;
import com.yuyashuai.frameanimation.FrameAnimation;

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
        layoutAnim();
        mContainer.setOnClickListener(v -> {
            layoutAnim();
        });
        mBtnUpTemp.setOnClickListener(this);
        mBtnDownTemp.setOnClickListener(this);
        mBtnUpTimer.setOnClickListener(this);
        mBtnDownTimer.setOnClickListener(this);

        mProgress.mWaveBgView.setRepeatMode(FrameAnimation.RepeatMode.INFINITE);
        mProgress.mWaveBgView.setFrameInterval(34);
        mProgress.mWaveBgView.setSupportInBitmap(true);
        mProgress.mWaveBgView.playAnimationFromAssets("wave_version1");
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
                //减温
                temperature = 300;
                mHandler.removeCallbacksAndMessages(null);
                mHandler.sendEmptyMessageDelayed(102, 0);
                break;

            case R.id.btn_3:
                //正计时
                temperature = 300;
                mHandler.removeCallbacksAndMessages(null);
                mHandler.sendEmptyMessageDelayed(103, 0);
                break;
            case R.id.btn_4:
                //倒计时
                temperature = 300;
                mHandler.removeCallbacksAndMessages(null);
                mHandler.sendEmptyMessageDelayed(104, 0);
                break;
            default:
        }
    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 101:
                    temperature = temperature + 1 + mRandom.nextInt(50);
                    if (temperature >= 300) {
                        temperature = 300;
                        mHandler.removeCallbacksAndMessages(null);
                    } else {
                        mHandler.sendEmptyMessageDelayed(101, 2000);
                    }
                    mProgress.setTemperature(temperature, 300);
                    break;
                case 102:
                    //模拟减温，随机减温0-30度之间
                    temperature = temperature - mRandom.nextInt(30);
                    if (temperature < 0) {
                        temperature = 0;
                        mHandler.removeCallbacksAndMessages(null);
                    } else {
                        mHandler.sendEmptyMessageDelayed(102, 2000);
                    }
                    mProgress.setTemperature(temperature, 300);
                    break;
                case 103:
                    //模拟保温 正计时
                    temperature = 300;
                    mProgress.setTemperature(temperature, 300);
                    break;
                case 104:
                    //模拟倒计时

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
                    .setDuration(200)
                    .withLayer()
                    .start();

            mBtnContainer.setVisibility(View.VISIBLE);
            mProgress.animate()
                    .translationY(0)
                    .setDuration(200)
                    .scaleX(1f)
                    .scaleY(1f)
                    .withLayer()
                    .start();
        } else {
            //按钮上移
            mBtnContainer.animate()
                    .translationY(0F)
                    .setDuration(200)
                    .withLayer()
                    .start();

            mProgress.animate()
                    .translationY(-250F)
                    .scaleX(0.8f)
                    .scaleY(0.8f)
                    .setDuration(200)
                    .withLayer()
                    .start();
        }
        tagger = !tagger;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mProgress.mWaveBgView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mProgress.mWaveBgView.onResume();
    }
}
