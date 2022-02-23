package com.chenyangqi.event.dispatch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * @author : ChenYangQi
 * date   : 2022/2/23 23:36
 * desc   :
 */
public class MyListView extends ListView {
    public MyListView(Context context) {
        super(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("test_y", "----1");
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("test_y", "当前距离顶部距离y=" + getTranslationY());
                if (getTranslationY() < 300) {
                    requestDisallowInterceptTouchEvent(true);
                }
                break;
            default:
                break;

        }
        return super.dispatchTouchEvent(ev);
    }

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                requestDisallowInterceptTouchEvent(true);
//                break;
//            case MotionEvent.ACTION_MOVE:
//                Log.d("test_y", "当前距离顶部距离y=" + getY());
//                if (getY() < -300) {
//                    requestDisallowInterceptTouchEvent(true);
//                }
//                break;
//            default:
//                break;
//
//        }
//        return super.onInterceptTouchEvent(ev);
//    }
}
