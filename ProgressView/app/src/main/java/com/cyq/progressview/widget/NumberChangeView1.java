package com.cyq.progressview.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
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
public class NumberChangeView1 extends FrameLayout {
    private TextView mTvFirst;
    private TextView mTvSecond;
    private ValueAnimator mUpAnim;
    private ValueAnimator mDownAnim;
    private int mHeight;

    public NumberChangeView1(@NonNull Context context) {
        this(context, null);
    }

    public NumberChangeView1(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberChangeView1(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(getContext()).inflate(R.layout.number_item_layout, this, true);
        mTvFirst = findViewById(R.id.tv_number_one);
        mTvSecond = findViewById(R.id.tv_number_tow);
        init();
    }

    private class MyRunnable implements Runnable {
        @Override
        public void run() {
            int mFirstCurrentValue = Integer.parseInt(mTvFirst.getText().toString());
            int mSecondCurrentValue = Integer.parseInt(mTvSecond.getText().toString());
            int mFirstNextValue = (mFirstCurrentValue + 2) % 10;
            int mSecondNextValue = (mSecondCurrentValue + 2) % 10;
            mTvFirst.setText(String.valueOf(mFirstNextValue));
            mTvSecond.setText(String.valueOf(mSecondNextValue));
            mDownAnim.start();
        }
    }

    private MyRunnable mNumberRunnable = new MyRunnable();

    private void init() {
        mHeight = Utils.dip2px(80, getContext());
        mDownAnim = ValueAnimator.ofFloat(0F, 1F);
        mDownAnim.setDuration(600);
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
                postDelayed(mNumberRunnable, 500);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        mDownAnim.start();
    }

    /**
     * 设置目标数字
     *
     * @param targetNumber
     */
    public void setTargetNumber(int targetNumber) {

    }
}
