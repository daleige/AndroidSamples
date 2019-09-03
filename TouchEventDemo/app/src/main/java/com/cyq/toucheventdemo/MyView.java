package com.cyq.toucheventdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Create by 陈扬齐
 * Create on 2019-09-03
 * description:
 */
public class MyView extends View {
    private static final String Log = "TouchEventDemo";

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        android.util.Log.d(Log, "MyView:dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        android.util.Log.d(Log, "MyView:onTouchEvent");
        return super.onTouchEvent(event);
    }
}
