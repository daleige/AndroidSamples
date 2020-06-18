package com.cyq.progressview.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cyq.progressview.R;

/**
 * @author : ChenYangQi
 * date   : 2020/5/23 14:22
 * desc   :有动画效果的改变的数字控件
 */
public class NumberChangeView extends FrameLayout {
    private TextView mTvFirst;
    private TextView mTvSecond;
    private ValueAnimator mUpAnim;
    private ValueAnimator mDownAnim;

    public NumberChangeView(@NonNull Context context) {
        this(context, null);
    }

    public NumberChangeView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberChangeView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(getContext()).inflate(R.layout.number_item_layout, this, true);
        mTvFirst = findViewById(R.id.tv_number_one);
        mTvSecond = findViewById(R.id.tv_number_tow);
        init();
    }

    private void init() {
        mUpAnim = ValueAnimator.ofFloat(0F, 1F);
        mUpAnim.setDuration(1000);
        mUpAnim.setInterpolator(new BounceInterpolator());
        mUpAnim.setRepeatCount(1);
        mUpAnim.setRepeatMode(ValueAnimator.RESTART);
        mUpAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                mTvFirst.setTranslationY(-mTvFirst.getHeight() * value);
            }
        });
        mUpAnim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mDownAnim.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        mDownAnim = ValueAnimator.ofFloat(0F, 1F);
        mDownAnim.setDuration(1000);
        mDownAnim.setInterpolator(new BounceInterpolator());
        mDownAnim.setRepeatCount(1);
        mDownAnim.setRepeatMode(ValueAnimator.RESTART);
        mDownAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                mTvSecond.setTranslationY( mTvSecond.getHeight() * value);
            }
        });
        mDownAnim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mUpAnim.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        mUpAnim.start();
    }

    /**
     * 设置目标数字
     *
     * @param targetNumber
     */
    public void setTargetNumber(int targetNumber) {

    }
}
