package com.cyq.customview.intercept;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.cyq.customview.R;

/**
 * Time: 2020/6/1 11:41 PM
 * Author: ChenYangQi
 * Description:
 */
public class BottomView extends LinearLayout {
    public BottomView(Context context) {
        this(context, null);
    }

    public BottomView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(getContext()).inflate(R.layout.bottom_layout_view, this, true);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercepted = false;

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                intercepted = false;
                break;
            case MotionEvent.ACTION_MOVE:
                intercepted = true;
                break;
            case MotionEvent.ACTION_UP:
                intercepted = false;
                break;
        }
        return intercepted;
    }
}
