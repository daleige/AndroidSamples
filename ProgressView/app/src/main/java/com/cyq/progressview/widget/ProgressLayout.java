package com.cyq.progressview.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cyq.progressview.R;
import com.yuyashuai.frameanimation.FrameAnimation;
import com.yuyashuai.frameanimation.FrameAnimationView;


/**
 * @author : ChenYangQi
 * date   : 2020/5/23 13:41
 * desc   : 自定义组合布局 底部的扇形粒子动画+数字切换
 */
public class ProgressLayout extends FrameLayout {
    private MySmartProgressView mMySmartProgressView;
    private AnimNumberView mAnimNumberView;
    private OnCompleteListener mCompleteListener;
    private FrameAnimationView mInitAnimView;
    /**
     * 此版本去掉波动背景，先预留在这里，可续可能会重新使用
     */
    private FrameAnimationView mWaveBgView;

    /**
     * 用于标记是预热还是保温模式
     */
    private boolean isKeepWare = false;

    public ProgressLayout(@NonNull Context context) {
        this(context, null);
    }

    public ProgressLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.widget_progress_progress_view_layout, this, true);
        mMySmartProgressView = findViewById(R.id.mMySmartProgressView);
        mAnimNumberView = findViewById(R.id.mTempNumberView);
        //mWaveBgView = findViewById(R.id.mWaveBgView);
        mInitAnimView = findViewById(R.id.mInitAnimView);

        mInitAnimView.setVisibility(VISIBLE);
        mInitAnimView.setRepeatMode(FrameAnimation.RepeatMode.ONCE);
        mInitAnimView.setFrameInterval(17);
        mInitAnimView.setSupportInBitmap(true);
        mInitAnimView.setAnimationListener(new FrameAnimation.FrameAnimationListener() {
            @Override
            public void onAnimationStart() {

            }

            @Override
            public void onAnimationEnd() {
                mMySmartProgressView.setVisibility(VISIBLE);
                mAnimNumberView.setVisibility(VISIBLE);
            }

            @Override
            public void onProgress(float v, int i, int i1) {

            }
        });
        postDelayed(new Runnable() {
            @Override
            public void run() {
                mInitAnimView.playAnimationFromAssets("init_jndicator_version1");
            }
        }, 1000);
    }

    /**
     * 设置当前显示温度
     *
     * @param temperature       当前温度
     * @param targetTemperature 目标温度
     */
    public void setTemperature(float temperature, float targetTemperature) {
        checkIsKeepWareState(false);
        //温度设置
        mMySmartProgressView.setCurrentTemperature(temperature, targetTemperature);
        //动画数字控件设置温度模式
        mAnimNumberView.setTemperature((int) temperature, AnimNumberView.TEMPERATURE_MODE);
    }

    /**
     * 设置正计时 or 倒计时
     * 如果是正计时传入second>0表示设置了目标时间，达到时间停止计时并回调到达的转态
     * 如果是倒计时，计时结束回调计时结束的状态
     *
     * @param second
     * @param timerMode
     */
    public void setTimer(int second, int timerMode) {
        checkIsKeepWareState(true);
        mMySmartProgressView.startKeepWare();
        mAnimNumberView.setTimer(second, timerMode);
        mAnimNumberView.setOnTimerCompleteListener(new AnimNumberView.OnTimerComplete() {
            @Override
            public void onComplete() {
                if (mCompleteListener != null) {
                    mCompleteListener.onComplete();
                }
            }
        });
    }

    /**
     * 正计时，无最终到达时间限制
     *
     * @param mode
     */
    public void setTimer(int mode) {
        if (mode == AnimNumberView.UP_TIMER) {
            setTimer(-1, mode);
        }
    }

    private void checkIsKeepWareState(boolean mode) {
//        if (isKeepWare == mode) {
//            return;
//        }
//        isKeepWare = mode;
//        if (mode) {
//            mWaveBgView.setVisibility(VISIBLE);
//            if (mInitAnimView.isPlaying()) {
//                mInitAnimView.stopAnimationSafely();
//            }
//            mWaveBgView.setRepeatMode(FrameAnimation.RepeatMode.INFINITE);
//            mWaveBgView.setFrameInterval(34);
//            mWaveBgView.setSupportInBitmap(true);
//            mWaveBgView.playAnimationFromAssets("wave_version1");
//        } else {
//            mWaveBgView.setVisibility(GONE);
//            if (mWaveBgView.isPlaying()) {
//                mWaveBgView.stopAnimationSafely();
//            }
//        }
    }

    public void setOnCompleteListener(OnCompleteListener mCompleteListener) {
        this.mCompleteListener = mCompleteListener;
    }

    public interface OnCompleteListener {
        /**
         * 计时完成的回调
         */
        void onComplete();
    }

    public void onPause() {
        //mWaveBgView.onPause();
        mInitAnimView.onResume();
    }

    public void onResume() {
        //mWaveBgView.onResume();
        mInitAnimView.onResume();
    }
}
