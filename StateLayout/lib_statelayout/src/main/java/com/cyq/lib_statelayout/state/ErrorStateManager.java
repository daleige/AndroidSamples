package com.cyq.lib_statelayout.state;

import android.content.Context;
import android.view.View;

import com.cyq.lib_statelayout.R;
import com.cyq.lib_statelayout.interfaces.IErrorState;

/**
 * Time: 2019-12-01 22:24
 * Author: ChenYangQi
 * Description:错误状态管理类
 */
public class ErrorStateManager implements IErrorState {

    private OnRetryClickListener mOnRetryClickListener;
    private View mView;

    @Override
    public View getView(Context context) {
        if (mView == null) {
            mView = View.inflate(context, R.layout.error_state_layout, null);
        }
        return mView;
    }

    @Override
    public void hide() {
        if (mView != null) {
            mView.setVisibility(View.GONE);
        }
    }

    @Override
    public void show() {
        if (mView != null) {
            mView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setRetryClickListener(OnRetryClickListener retryClickListener) {
        this.mOnRetryClickListener = retryClickListener;
        if (mView != null) {
            mView.findViewById(R.id.btn_retry).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnRetryClickListener != null) {
                        mOnRetryClickListener.onRetryClicked();
                    }
                }
            });
        }
    }

}
