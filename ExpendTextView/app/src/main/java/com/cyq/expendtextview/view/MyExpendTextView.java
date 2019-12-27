package com.cyq.expendtextview.view;

import android.content.Context;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
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
                        close();
                        isOpen = false;
                        mOpneAndCloseTaggerListener.close();
                    }
                } else {
                    if (mOpneAndCloseTaggerListener != null) {
                        open();
                        isOpen = true;
                        mOpneAndCloseTaggerListener.open();
                    }
                }
            }
        });
    }

    public void open() {
        setMaxLines(Integer.MAX_VALUE);
    }

    public void close() {
        setMaxLines(minLines);
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
