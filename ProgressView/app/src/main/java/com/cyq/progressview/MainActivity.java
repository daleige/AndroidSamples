package com.cyq.progressview;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.cyq.progressview.button.MyButton;
import com.cyq.progressview.widget.ProgressLayout;

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_1:
                //预热
                upTemperature();
                break;

            case R.id.btn_2:
                //减温
                downTemperature();
                break;

            case R.id.btn_3:
                //正计时
                upTimer();
                break;
            case R.id.btn_4:
                //倒计时
                downTimer();
                break;
            default:
        }
    }

    /**
     * 模拟预热到300°C
     */
    private void upTemperature() {

    }

    /**
     * 模拟降温到0°C
     */
    private void downTemperature() {

    }

    /**
     * 正计时
     */
    private void upTimer() {

    }

    /**
     * 倒计时
     */
    private void downTimer() {

    }

    /**
     * 触摸执行动画
     */
    private void layoutAnim() {
        if (tagger) {
            //按钮下移
            mBtnContainer.animate()
                    .translationY(600F)
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
}
