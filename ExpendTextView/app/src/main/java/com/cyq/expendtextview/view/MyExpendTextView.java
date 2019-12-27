package com.cyq.expendtextview.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * @author : ChenYangQi
 * date   : 2019/12/21 8:39
 * desc   : 文字展开收缩控件，支持展开隐藏的动画
 */
public class MyExpendTextView extends AppCompatTextView {
    private int minLines = 3;
    private boolean isOpen = false;
    private ValueAnimator mOpenAnim, mCloseAnim;

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
            mOpenAnim.setDuration(1000);
        }
        mOpenAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float f = (float) animation.getAnimatedValue();
                MyExpendTextView.this.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("test", "---------open------:" + (int) (minHeight + (maxHeight - minHeight) * f));
                        MyExpendTextView.this.getLayoutParams().height = (int) (minHeight + (maxHeight - minHeight) * f);
                        invalidate();
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
            mCloseAnim.setDuration(1000);
        }
        mCloseAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float f = (float) animation.getAnimatedValue();
                MyExpendTextView.this.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("test", "---------close------:" + (int) (maxHeight - (maxHeight - minHeight) * f));
                        MyExpendTextView.this.getLayoutParams().height = (int) (maxHeight - (maxHeight - minHeight) * f);
                        invalidate();
                    }
                });
            }
        });
    }

    private int minHeight, maxHeight;

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
