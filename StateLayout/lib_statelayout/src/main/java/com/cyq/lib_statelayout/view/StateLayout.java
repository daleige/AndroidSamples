package com.cyq.lib_statelayout.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.cyq.lib_statelayout.interfaces.IErrorState;
import com.cyq.lib_statelayout.state.EmptyStateManager;
import com.cyq.lib_statelayout.state.ErrorStateManager;
import com.cyq.lib_statelayout.state.LoadingStateManager;

/**
 * @Author: ChenYangQi
 * Time: 2019-12-01 22:22
 * Description:自定义多状态ViewGroup
 */
public class StateLayout extends FrameLayout implements ErrorStateManager.OnRetryListener {
    /**
     * 展示自定义View的临时占位变量
     */
    private View tempCustomView;
    private View tempView;
    private ErrorStateManager mErrorStateManager;
    private LoadingStateManager mLoadingStateManager;
    private EmptyStateManager mEmptyStateManager;
    private OnRetryClickListener mRetryClickListener;

    public StateLayout(Context context) {
        this(context, null);
    }

    public StateLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StateLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化几种状态布局管理类
     */
    private void init() {
        mErrorStateManager = new ErrorStateManager(this);
        mLoadingStateManager = new LoadingStateManager();
        mEmptyStateManager = new EmptyStateManager();
    }

    /**
     * 展示错误状态布局
     */
    public void showError() {
        if (mErrorStateManager == null) {
            mErrorStateManager = new ErrorStateManager(this);
        }
        if (tempView == mErrorStateManager.getView(getContext())) {
            return;
        }
        tempView = mErrorStateManager.getView(getContext());
        removeStateView();
        addView(tempView);
        getTestChildCount();
    }

    /**
     * 展示加载中状态布局
     */
    public void showLoading() {
        if (mLoadingStateManager == null) {
            mLoadingStateManager = new LoadingStateManager();
        }
        if (tempView == mLoadingStateManager.getView(getContext())) {
            return;
        }
        tempView = mLoadingStateManager.getView(getContext());
        removeStateView();
        addView(tempView);
        getTestChildCount();
    }

    /**
     * 展示空数据状态
     */
    public void showEmpty() {
        if (mEmptyStateManager == null) {
            mEmptyStateManager = new EmptyStateManager();
        }
        if (tempView == mEmptyStateManager.getView(getContext())) {
            return;
        }
        tempView = mEmptyStateManager.getView(getContext());
        removeStateView();
        addView(tempView);
        getTestChildCount();
    }

    /**
     * 展示内容
     */
    public void showContent() {
        if (mErrorStateManager != null) {
            removeView(mErrorStateManager.getView(getContext()));
        }
        if (mLoadingStateManager != null) {
            removeView(mLoadingStateManager.getView(getContext()));
        }
        if (mEmptyStateManager != null) {
            removeView(mEmptyStateManager.getView(getContext()));
        }
        if (tempCustomView != null) {
            removeView(tempCustomView);
        }
        removeStateView();
        getTestChildCount();
    }

    /**
     * 展示自定义View
     */
    public void showCustomView(View view) {
        if (view == null) {
            throw new NullPointerException("StateLayout:custom view is empty");
        }
        if (tempView == view) {
            return;
        }
        removeStateView();
        tempView = view;
        tempCustomView = view;
        addView(tempView);
        getTestChildCount();
    }

    /**
     * 状态页面变更的时候，移除之前的状态页，避免重复addView造成内存泄漏
     */
    private void removeStateView() {
        if (mErrorStateManager != null) {
            removeView(mErrorStateManager.getView(getContext()));
        }
        if (mLoadingStateManager != null) {
            removeView(mLoadingStateManager.getView(getContext()));
        }
        if (mEmptyStateManager != null) {
            removeView(mEmptyStateManager.getView(getContext()));
        }
        if (tempCustomView != null) {
            removeView(tempCustomView);
        }
    }

    /**
     * 重试按钮点击的回调
     */
    @Override
    public void onRetry() {
        if (mRetryClickListener != null) {
            mRetryClickListener.onClick();
        }
    }

    /**
     * 点击重试接口
     */
    public interface OnRetryClickListener {
        void onClick();
    }

    /**
     * 重试点击事件得set方法
     *
     * @param mRetryClickListener
     */
    public void setRetryClickListener(OnRetryClickListener mRetryClickListener) {
        this.mRetryClickListener = mRetryClickListener;
    }

    public void getTestChildCount() {
        Log.i("test","子元素个数：" + getChildCount());
    }
}
