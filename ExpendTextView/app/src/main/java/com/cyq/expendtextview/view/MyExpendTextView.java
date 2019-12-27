package com.cyq.expendtextview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
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
                    close();
                    isOpen = false;
                } else {
                    open();
                    isOpen = true;
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

    private int dp2px(Context context, float dpValue) {
        int res = 0;
        final float scale = context.getResources().getDisplayMetrics().density;
        if (dpValue < 0) {
            res = -(int) (-dpValue * scale + 0.5f);
        } else {
            res = (int) (dpValue * scale + 0.5f);
        }
        return res;
    }
}
