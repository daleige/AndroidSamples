package com.cyq.toucheventdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Create by 陈扬齐
 * Create on 2019-09-03
 * description:
 */
public class MyViewGroup extends FrameLayout {
    private static final String Log = "TouchEventDemo";

    public MyViewGroup(@NonNull Context context) {
        super(context);
    }

    public MyViewGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        android.util.Log.d(Log, "MyViewGroup:dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        android.util.Log.d(Log, "MyViewGroup:onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        android.util.Log.d(Log, "MyViewGroup:onTouchEvent");
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                android.util.Log.d(Log, "MyViewGroup:手指按下");
//                break;
//            case MotionEvent.ACTION_MOVE:
//                android.util.Log.d(Log, "MyViewGroup:手指移动");
//                break;
//            case MotionEvent.ACTION_UP:
//                android.util.Log.d(Log, "MyViewGroup:手指抬起");
//                break;
//        }

        return super.onTouchEvent(event);
    }
}
