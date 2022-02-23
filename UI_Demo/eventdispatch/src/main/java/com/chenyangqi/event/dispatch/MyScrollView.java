package com.chenyangqi.event.dispatch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * @author : ChenYangQi
 * date   : 2022/2/23 23:29
 * desc   :
 */
public class MyScrollView extends ScrollView {
    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * 内部拦截处理滑动冲突，ViewGroup拦截掉除ACTION_DOWN外的所有事件
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("test_y", "**当前距离顶部距离y=" + getY());
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            return super.onInterceptTouchEvent(ev);
        }
        return true;
    }
}
