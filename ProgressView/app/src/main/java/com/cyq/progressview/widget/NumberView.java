package com.cyq.progressview.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cyq.progressview.R;
import com.cyq.progressview.utils.Utils;

/**
 * @author : ChenYangQi
 * date   : 2020/5/23 14:22
 * desc   :有动画效果的改变的数字控件
 */
public class NumberView extends FrameLayout {
    public static final int UP_ANIMATOR_MODE = 54536;
    public static final int DOWN_ANIMATOR_MODE = 87981;
    private int UP_OR_DOWN_MODE = UP_ANIMATOR_MODE;
    private TextView mTvFirst;
    private TextView mTvSecond;
    private ValueAnimator mNumberAnim;
    private int mHeight;
    private int mCurrentValue;

    private int width = 0;
    private int height = 0;
    private int textSize = 0;

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
        mHeight = Utils.dip2px(95, getContext());
        mNumberAnim = ValueAnimator.ofFloat(0F, 1F);
        mNumberAnim.setDuration(500);
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
     * 设置下一位数字的值
     *
     * @param value 个，十，百位的值
     */
    public void setCurrentValue(int value, int mode) {
        if (value == mCurrentValue) {
            return;
        }
        UP_OR_DOWN_MODE = mode;
        //判断数字是增加还是减少，进而确定不同的动画效果
        mTvFirst.setText(String.valueOf(mCurrentValue));
        mTvSecond.setText(String.valueOf(value));
        if (mNumberAnim.isRunning()) {
            mNumberAnim.cancel();
        }
        mNumberAnim.start();
        mCurrentValue = value;
    }

    private int mTrueValue = 0;

    /**
     * 设置下一位数值
     *
     * @param value     对应个、十、百位的数值
     * @param trueValue 全部数值 比如123
     * @param a         用来重载方法的，参数无实际用途
     */
    public void setCurrentValue(int value, int trueValue, int a) {
        if (trueValue > mTrueValue) {
            UP_OR_DOWN_MODE = UP_ANIMATOR_MODE;
        } else {
            UP_OR_DOWN_MODE = DOWN_ANIMATOR_MODE;
        }
        mTrueValue = trueValue;
        setCurrentValue(value, UP_OR_DOWN_MODE);
    }

    private LinearLayout.LayoutParams containerLp;
    private FrameLayout.LayoutParams tvFirstLp;
    private FrameLayout.MarginLayoutParams tvSecondLp;

    public void setLayoutSize(int w, int h, int s) {
        if (this.width == w) {
            return;
        }
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
