package com.cyq.progressview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cyq.progressview.R;
import com.cyq.progressview.utils.Utils;
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
    private int height;
    private int width;
    /**
     * 用于标记是预热还是保温模式
     */
    private boolean isKeepWare = false;
    private LayoutParams frameAnimationViewLp;
    private LayoutParams animNumberViewLp;

    public ProgressLayout(@NonNull Context context) {
        this(context, null);
    }

    public ProgressLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ProgressLayout);
        width = array.getDimensionPixelSize(R.styleable.ProgressLayout_progressLayout_width, Utils.dip2px(318, getContext()));
        height = array.getDimensionPixelOffset(R.styleable.ProgressLayout_progressLayout_height, Utils.dip2px(318, getContext()));
        array.recycle();
        setMeasuredDimension(width, height);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    private void init() {
        mInitAnimView = new FrameAnimationView(getContext());
        frameAnimationViewLp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        frameAnimationViewLp.gravity = Gravity.CENTER;
        mInitAnimView.setLayoutParams(frameAnimationViewLp);

        mMySmartProgressView = new MySmartProgressView(getContext(), width, height);
        mMySmartProgressView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        mAnimNumberView = new AnimNumberView(getContext());
        animNumberViewLp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        animNumberViewLp.gravity = Gravity.CENTER;
        mAnimNumberView.setLayoutParams(animNumberViewLp);

        addView(mInitAnimView);
        addView(mMySmartProgressView);
        addView(mAnimNumberView);
        mMySmartProgressView.setVisibility(GONE);
        mAnimNumberView.setVisibility(GONE);

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
        mMySmartProgressView.startKeepWare();
        mAnimNumberView.setTimer(second, timerMode);
        mAnimNumberView.setOnTimerCompleteListener(() -> {
            if (mCompleteListener != null) {
                mCompleteListener.onComplete();
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
        mInitAnimView.onResume();
    }

    public void onResume() {
        mInitAnimView.onResume();
    }
}
