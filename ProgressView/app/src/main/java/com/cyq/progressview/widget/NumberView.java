package com.cyq.progressview.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.BounceInterpolator;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cyq.progressview.R;
import com.cyq.progressview.Utils;

/**
 * @author : ChenYangQi
 * date   : 2020/5/23 14:22
 * desc   :有动画效果的改变的数字控件
 */
public class NumberView extends FrameLayout {
    private TextView mTvFirst;
    private TextView mTvSecond;
    private ValueAnimator mUpAnim;
    private ValueAnimator mDownAnim;
    private int mHeight;
    private int mCurrentValue;
    private int mTemperature;

    /**
     * 是升序还是降序
     */
    private boolean mAscending;

    public NumberView(@NonNull Context context) {
        this(context, null);
    }

    public NumberView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(getContext()).inflate(R.layout.widget_progress_number_item_layout, this, true);
        mTvFirst = findViewById(R.id.tv_number_one);
        mTvSecond = findViewById(R.id.tv_number_tow);
        init();
    }

    private void init() {
        mHeight = Utils.dip2px(80, getContext());
        mDownAnim = ValueAnimator.ofFloat(0F, 1F);
        mDownAnim.setDuration(500);
        mDownAnim.setInterpolator(new BounceInterpolator());
        mDownAnim.setRepeatCount(0);
        mDownAnim.setRepeatMode(ValueAnimator.RESTART);
        mDownAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                if (mAscending) {
                    mTvFirst.setTranslationY(-mHeight * value);
                    mTvSecond.setTranslationY(-mHeight * value);
                } else {
                    mTvFirst.setTranslationY(mHeight * value);
                    mTvSecond.setTranslationY(-2 * mHeight + mHeight * value);
                }
            }
        });
    }

    public int getCurrentValue() {
        return mCurrentValue;
    }

    /**
     * 设置下一位数字的值
     *
     * @param value       个，十，百位的值
     * @param temperature 真实的值
     */
    public void setCurrentValue(int value, int temperature) {
        if (temperature == this.mTemperature || value == mCurrentValue) {
            return;
        }
        //判断数字是增加还是减少，进而确定不同的动画效果
        mAscending = temperature > mCurrentValue;
        mTvFirst.setText(String.valueOf(mCurrentValue));
        mTvSecond.setText(String.valueOf(value));
        mDownAnim.start();
        mTemperature = temperature;
        mCurrentValue = value;
    }
}
