package com.cyq.expendtextview.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
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
    private boolean isExpendable;
    //动画时长
    private int duration = 200;

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
        setEllipsize(TextUtils.TruncateAt.END);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOpen) {
                    if (mOpneAndCloseTaggerListener != null) {
                        isOpen = false;
                        startCloseAnim();
                    }
                } else {
                    isOpen = true;
                    startOpenAnim();
                }
            }
        });
    }

    /**
     * 展开动画
     */
    private void startOpenAnim() {
        if (mOpneAndCloseTaggerListener != null) {
            mOpneAndCloseTaggerListener.open();
        }
        if (mOpenAnim == null) {
            mOpenAnim = ValueAnimator.ofFloat(0, 1);
            mOpenAnim.setDuration(duration);
        }

        mOpenAnim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                setMaxLines(Integer.MAX_VALUE);
            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {
                setMaxLines(Integer.MAX_VALUE);
            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

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
        if (mOpneAndCloseTaggerListener != null) {
            mOpneAndCloseTaggerListener.close();
        }
        if (mCloseAnim == null) {
            mCloseAnim = ValueAnimator.ofFloat(0, 1);
            mCloseAnim.setDuration(duration);
        }

        mCloseAnim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                setMaxLines(3);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                setMaxLines(3);
            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

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
        isOpen = true;
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
        isOpen = false;
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
    public void init(int minHeight, int maxHeight, boolean isExpendable) {
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.isExpendable = isExpendable;
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
