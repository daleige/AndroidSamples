package com.cyq.progressview.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cyq.progressview.R;

/**
 * @author : ChenYangQi
 * date   : 2020/5/23 14:22
 * desc   :有动画效果的改变的数字控件
 */
public class NumberView extends FrameLayout {
    public static final String POSITION_ONE = "POSITION_ONE";
    public static final String POSITION_TOW = "POSITION_TOW";
    public static final String POSITION_THREE = "POSITION_THREE";
    public static final String POSITION_FORT = "POSITION_FORT";
    public static final String POSITION_FIVE = "POSITION_FIVE";
    public static final String POSITION_SIX = "POSITION_SIX";
    public static final int UP_ANIMATOR_MODE = 54536;
    public static final int DOWN_ANIMATOR_MODE = 87981;
    private int UP_OR_DOWN_MODE = UP_ANIMATOR_MODE;
    private final int DELAY_DURATION = 200;
    private TextView mTvFirst;
    private TextView mTvSecond;
    private ValueAnimator mNumberAnim;
    private int mHeight = 0;
    private int mCurrentValue;
    private int mTrueValue = 0;
    private int width = 0;
    private int height = 0;
    private int textSize = 0;
    /**
     * 不同位置需要延迟执行动画
     */
    private int startDelay = 0;
    private LinearLayout.LayoutParams containerLp;
    private FrameLayout.LayoutParams tvFirstLp;
    private FrameLayout.MarginLayoutParams tvSecondLp;

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
        mNumberAnim = ValueAnimator.ofFloat(0F, 1F);
        mNumberAnim.setDuration(400);
        mNumberAnim.setInterpolator(new OvershootInterpolator());
        mNumberAnim.setRepeatCount(0);
        mNumberAnim.setRepeatMode(ValueAnimator.RESTART);
        mNumberAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                if (UP_OR_DOWN_MODE == UP_ANIMATOR_MODE) {
                    mTvFirst.setTranslationY(-mHeight * value);
                    mTvSecond.setTranslationY(-mHeight * value);
                } else {
                    mTvFirst.setTranslationY(mHeight * value);
                    mTvSecond.setTranslationY(-2 * mHeight + mHeight * value);
                }
            }
        });
    }

    /**
     * 设置下一位数值
     *
     * @param value     对应个、十、百位的数值
     * @param trueValue 全部数值 比如123
     * @param a         用来重载方法的，参数无实际用途
     */
    public void setCurrentValue(String position, int value, int trueValue, int a) {
        if (trueValue > mTrueValue) {
            UP_OR_DOWN_MODE = UP_ANIMATOR_MODE;
        } else {
            UP_OR_DOWN_MODE = DOWN_ANIMATOR_MODE;
        }
        mTrueValue = trueValue;
        //setStartDelay(position);
        setCurrentValue(position, value, UP_OR_DOWN_MODE);
    }

    /**
     * 设置下一位数字的值
     *
     * @param value 个，十，百位的值
     */
    public void setCurrentValue(String position, int value, int mode) {
        if (value == mCurrentValue) {
            return;
        }
        UP_OR_DOWN_MODE = mode;
        setStartDelay(position);
        //判断数字是增加还是减少，进而确定不同的动画效果
        mTvFirst.setText(String.valueOf(mCurrentValue));
        mTvSecond.setText(String.valueOf(value));
        if (mNumberAnim.isRunning()) {
            mNumberAnim.cancel();
        }
        mNumberAnim.setDuration(400 + startDelay);
        //mNumberAnim.setStartDelay(startDelay);
        mNumberAnim.start();
        mCurrentValue = value;
    }

    /**
     * 计算对应位置的延迟时间
     *
     * @param position
     */
    private void setStartDelay(String position) {
        if (position.equals(POSITION_ONE)) {
            startDelay = 0;
        } else if (position.equals(POSITION_TOW)) {
            startDelay = DELAY_DURATION;
        } else if (position.equals(POSITION_THREE)) {
            startDelay = DELAY_DURATION * 2;
        } else if (position.equals(POSITION_FORT)) {
            startDelay = DELAY_DURATION * 3;
        } else if (position.equals(POSITION_FIVE)) {
            startDelay = DELAY_DURATION * 4;
        } else if (position.equals(POSITION_SIX)) {
            startDelay = DELAY_DURATION * 5;
        }
    }

    /**
     * 设置宽、高、字体大小
     *
     * @param w
     * @param h
     * @param s
     */
    public void setLayoutSize(int w, int h, int s) {
        if (this.width == w) {
            return;
        }
        this.mHeight = h;
        this.width = w;
        this.height = h;
        this.textSize = s;
        containerLp = new LinearLayout.LayoutParams(width, height);
        setLayoutParams(containerLp);
        tvFirstLp = new FrameLayout.LayoutParams(width, height);
        mTvFirst.setLayoutParams(tvFirstLp);
        mTvFirst.setGravity(Gravity.CENTER);
        mTvFirst.setTextSize(textSize);
        tvSecondLp = new FrameLayout.LayoutParams(width, height);
        tvSecondLp.topMargin = height;
        mTvSecond.setLayoutParams(tvSecondLp);
        mTvSecond.setGravity(Gravity.CENTER);
        mTvSecond.setTextSize(textSize);
    }
}
