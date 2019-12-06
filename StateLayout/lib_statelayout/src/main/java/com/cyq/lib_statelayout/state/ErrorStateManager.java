package com.cyq.lib_statelayout.state;

import android.content.Context;
import android.view.View;

import com.cyq.lib_statelayout.R;

/**
 * Time: 2019-12-01 22:24
 *
 * @Author: ChenYangQi
 * Description:错误状态管理类
 */
public class ErrorStateManager {
    private View mView;
    private OnRetryListener mRetryListener;

    public ErrorStateManager(OnRetryListener mRetryListener) {
        this.mRetryListener = mRetryListener;
    }

    public View getView(Context context) {
        if (mView == null) {
            mView = View.inflate(context, R.layout.error_state_layout, null);
        }

        mView.findViewById(R.id.btn_retry).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRetryListener != null) {
                    mRetryListener.onRetry();
                }
            }
        });

        return mView;
    }

    public interface OnRetryListener {
        void onRetry();
    }
}
