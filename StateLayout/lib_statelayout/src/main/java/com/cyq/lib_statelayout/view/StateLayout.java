package com.cyq.lib_statelayout.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.cyq.lib_statelayout.interfaces.IErrorState;
import com.cyq.lib_statelayout.state.ErrorStateManager;
import com.cyq.lib_statelayout.state.LoadingStateManager;

/**
 * Time: 2019-12-01 22:22
 * Author: ChenYangQi
 * Description:自定义多状态ViewGroup
 */
public class StateLayout extends FrameLayout {
    private ErrorStateManager mErrorStateManager;
    private LoadingStateManager mLoadingStatemanager;

    public StateLayout(Context context) {
        super(context, null);
    }

    public StateLayout(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public StateLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化几种状态布局管理类
     */
    private void init() {
        mErrorStateManager = new ErrorStateManager();
        mLoadingStatemanager = new LoadingStateManager();
    }

    /**
     * 展示错误状态布局
     */
    public void showError() {
        if (mErrorStateManager == null) {
            mErrorStateManager = new ErrorStateManager();
        }
        removeView(mErrorStateManager.getView(getContext()));
        addView(mErrorStateManager.getView(getContext()));
    }

    /**
     * 展示加载中状态布局
     */
    public void showLoading() {
        if (mLoadingStatemanager == null) {
            mLoadingStatemanager = new LoadingStateManager();
        }
        removeView(mLoadingStatemanager.getView(getContext()));
        addView(mLoadingStatemanager.getView(getContext()));
    }

    /**
     * 设置重试按钮布局
     *
     * @param onRetryClickListener 重试点击事件
     */
    public void setOnRetryClickListener(IErrorState.OnRetryClickListener onRetryClickListener) {
        if (mErrorStateManager == null) {
            showError();
        }
        if (mErrorStateManager != null) {
            mErrorStateManager.setRetryClickListener(onRetryClickListener);
        }
    }
}
