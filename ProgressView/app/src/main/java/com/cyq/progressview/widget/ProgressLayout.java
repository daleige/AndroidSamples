package com.cyq.progressview.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cyq.progressview.R;

/**
 * @author : ChenYangQi
 * date   : 2020/5/23 13:41
 * desc   : 自定义组合布局 底部的扇形粒子动画+数字切换
 */
public class ProgressLayout extends FrameLayout {
    public MySmartProgressView mMySmartProgressView;
    private AnimNumberView mAnimNumberView;
    private OnCompleteListener mCompleteListener;
    private int height;
    private int width;
    private int outerShaderWidth;
    private int circleStrokeWidth;
    private boolean isCleanMode;

    public ProgressLayout(@NonNull Context context) {
        this(context, null);
    }

    public ProgressLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ProgressLayout);
        width = array.getDimensionPixelSize(R.styleable.ProgressLayout_progressLayout_width,
                Utils.dip2px(328, getContext()));
        height = array.getDimensionPixelOffset(R.styleable.ProgressLayout_progressLayout_height,
                Utils.dip2px(328, getContext()));
        outerShaderWidth = array.getDimensionPixelOffset(R.styleable.ProgressLayout_progressLayout_outer_shader_width,
                Utils.dip2px(10, getContext()));
        circleStrokeWidth = array.getDimensionPixelOffset(R.styleable.ProgressLayout_progressLayout_circle_stroke_width,
                Utils.dip2px(13, getContext()));
        isCleanMode = array.getBoolean(R.styleable.ProgressLayout_progressLayout_clean_mode, false);
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
        mMySmartProgressView = new MySmartProgressView(getContext(), width, height, outerShaderWidth, circleStrokeWidth, isCleanMode);
        mMySmartProgressView.setDimension(width, height);
        LayoutParams mySmartProgressViewLp = new LayoutParams(width, height);
        mySmartProgressViewLp.gravity = Gravity.CENTER;
        mMySmartProgressView.setLayoutParams(mySmartProgressViewLp);

        mAnimNumberView = new AnimNumberView(getContext());
        LayoutParams animNumberViewLp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        animNumberViewLp.gravity = Gravity.CENTER;
        mAnimNumberView.setLayoutParams(animNumberViewLp);
        addView(mMySmartProgressView);
        addView(mAnimNumberView);
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
        mMySmartProgressView.setKeepWareMode();
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

    /**
     * 设置清洁模式
     *
     * @param second
     */
    public void setCleanMode(int second, OnCompleteListener completeListener) {
        isCleanMode = true;
        mCompleteListener = completeListener;
        mMySmartProgressView.setCleanMode(second, true);
        mAnimNumberView.setTimer(second, AnimNumberView.DOWN_TIMER);
        mAnimNumberView.setOnTimerCompleteListener(new AnimNumberView.OnTimerComplete() {
            @Override
            public void onComplete() {
                if (mCompleteListener != null) {
                    mCompleteListener.onComplete();
                }
            }
        });
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
}
