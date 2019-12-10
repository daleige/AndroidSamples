package com.cyq.library;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

import static com.cyq.library.ObservableScrollView.OnScrollListener.SCROLL_STATE_FLING;
import static com.cyq.library.ObservableScrollView.OnScrollListener.SCROLL_STATE_IDLE;
import static com.cyq.library.ObservableScrollView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL;

/**
 * Time: 2019-12-08 21:56
 * Author: ChenYangQi
 * Description:扩展ScrollView,增加滑动状态改变的回调
 */
public class ObservableScrollView extends ScrollView {

    public interface OnScrollListener {
        int SCROLL_STATE_IDLE = 0;
        int SCROLL_STATE_TOUCH_SCROLL = 1;
        int SCROLL_STATE_FLING = 2;

        /**
         * 滑动状态改变
         *
         * @param view
         * @param scrollState
         */
        void onScrollStateChanged(ObservableScrollView view, int scrollState);

        /**
         * 滑动中的各个方向移动距离
         *
         * @param view
         * @param isTouchScroll
         * @param l
         * @param t
         * @param oldl
         * @param oldt
         */
        void onScroll(ObservableScrollView view, boolean isTouchScroll, int l, int t, int oldl, int oldt);
    }

    private static final int CHECK_SCROLL_STOP_DELAY_MILLIS = 80;
    private static final int MSG_SCROLL = 1;

    private boolean mIsTouched = false;
    private int mScrollState = SCROLL_STATE_IDLE;

    private OnScrollListener mOnScrollListener;

    private final Handler mHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {

        private int mLastY = Integer.MIN_VALUE;

        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == MSG_SCROLL) {
                final int scrollY = getScrollY();
                if (!mIsTouched && mLastY == scrollY) {
                    mLastY = Integer.MIN_VALUE;
                    setScrollState(SCROLL_STATE_IDLE);
                } else {
                    mLastY = scrollY;
                    restartCheckStopTiming();
                }
                return true;
            }
            return false;
        }
    });

    private void restartCheckStopTiming() {
        mHandler.removeMessages(MSG_SCROLL);
        mHandler.sendEmptyMessageDelayed(MSG_SCROLL, CHECK_SCROLL_STOP_DELAY_MILLIS);
    }

    public ObservableScrollView(Context context) {
        super(context);
    }

    public ObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ObservableScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        mOnScrollListener = onScrollListener;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        handleDownEvent(ev);
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        handleUpEvent(ev);
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mIsTouched) {
            setScrollState(SCROLL_STATE_TOUCH_SCROLL);
        } else {
            setScrollState(SCROLL_STATE_FLING);
            restartCheckStopTiming();
        }
        if (mOnScrollListener != null) {
            mOnScrollListener.onScroll(this, mIsTouched, l, t, oldl, oldt);
        }
    }

    private void handleDownEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mIsTouched = true;
                break;
            default:
        }
    }

    private void handleUpEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mIsTouched = false;
                restartCheckStopTiming();
                break;
            default:
        }
    }

    private void setScrollState(int state) {
        if (mScrollState != state) {
            mScrollState = state;
            if (mOnScrollListener != null) {
                mOnScrollListener.onScrollStateChanged(this, state);
            }
        }
    }
}