package com.cyq.progressview.widget;

import android.animation.Animator;
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
    private int mCurrentValue = 0;
    private boolean firstInRanger = true;
    private int mFirstNextValue = 0;
    private int mSecondNextValue = 0;

    public NumberView(@NonNull Context context) {
        this(context, null);
    }

    public NumberView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(getContext()).inflate(R.layout.number_item_layout, this, true);
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
                mTvFirst.setTranslationY(-mHeight * value);
                mTvSecond.setTranslationY(-mHeight * value);
            }
        });
        mDownAnim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    public int getCurrentValue() {
        return mCurrentValue;
    }

    public void setCurrentValue(int value) {
        mTvFirst.setText(String.valueOf(mCurrentValue));
        mTvSecond.setText(String.valueOf(value));
        mDownAnim.start();
        this.mCurrentValue = value;
    }
}
