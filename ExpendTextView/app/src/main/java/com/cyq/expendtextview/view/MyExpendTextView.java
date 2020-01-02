package com.cyq.expendtextview.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * @author : ChenYangQi
 * date   : 2019/12/21 8:39
 * desc   : 文字展开收缩控件，支持展开隐藏的动画
 */
public class MyExpendTextView extends AppCompatTextView {
    private boolean isOpen = false;
    private ValueAnimator mOpenAnim;
    private ValueAnimator mCloseAnim;
    private int minHeight;
    private int maxHeight;
    //动画时长
    private int duration = 400;

    public MyExpendTextView(Context context) {
        this(context, null);
    }

    public MyExpendTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyExpendTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOpen) {
                    if (mOpneAndCloseTaggerListener != null) {
                        isOpen = false;
                        mOpneAndCloseTaggerListener.close();
                        startCloseAnim();
                    }
                } else {
                    if (mOpneAndCloseTaggerListener != null) {
                        isOpen = true;
                        mOpneAndCloseTaggerListener.open();
                        startOpenAnim();
                    }
                }
            }
        });
    }

    /**
     * 展开动画
     */
    private void startOpenAnim() {
        if (mOpenAnim == null) {
            mOpenAnim = ValueAnimator.ofFloat(0, 1);
            mOpenAnim.setDuration(duration);
        }
        final ViewGroup.LayoutParams layoutParams = getLayoutParams();
        mOpenAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float f = (float) animation.getAnimatedValue();
                MyExpendTextView.this.post(new Runnable() {
                    @Override
                    public void run() {
                        layoutParams.height = (int) (minHeight + ((maxHeight - minHeight) * f));
                        setLayoutParams(layoutParams);
                    }
                });
            }
        });
        mOpenAnim.start();
    }

    /**
     * 关闭动画
     */
    private void startCloseAnim() {
        if (mCloseAnim == null) {
            mCloseAnim = ValueAnimator.ofFloat(0, 1);
            mCloseAnim.setDuration(duration);
        }
        final ViewGroup.LayoutParams layoutParams = getLayoutParams();
        mCloseAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float f = (float) animation.getAnimatedValue();
                MyExpendTextView.this.post(new Runnable() {
                    @Override
                    public void run() {
                        layoutParams.height = (int) (maxHeight - ((maxHeight - minHeight) * f));
                        setLayoutParams(layoutParams);
                    }
                });
            }
        });
        mCloseAnim.start();
    }

    public void open() {
        MyExpendTextView.this.post(new Runnable() {
            @Override
            public void run() {
                ViewGroup.LayoutParams layoutParams = getLayoutParams();
                layoutParams.height = maxHeight;
                setLayoutParams(layoutParams);
            }
        });
    }

    public void close() {
        MyExpendTextView.this.post(new Runnable() {
            @Override
            public void run() {
                ViewGroup.LayoutParams layoutParams = getLayoutParams();
                layoutParams.height = minHeight;
                setLayoutParams(layoutParams);
            }
        });
    }

    /**
     * 初始化传入高度
     *
     * @param minHeight 收缩时的高度
     * @param maxHeight 展开时的高度
     */
    public void init(int minHeight, int maxHeight) {
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }

    public void setOpneAndCloseTaggerListener(OnOpenAndCloseTaggerListener mOpneAndCloseTaggerListener) {
        this.mOpneAndCloseTaggerListener = mOpneAndCloseTaggerListener;
    }

    private OnOpenAndCloseTaggerListener mOpneAndCloseTaggerListener;

    public interface OnOpenAndCloseTaggerListener {
        void open();

        void close();
    }
}
