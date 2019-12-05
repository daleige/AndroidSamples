package com.cyq.lib_statelayout.interfaces;

import android.content.Context;
import android.view.View;

/**
 * Time: 2019-12-01 22:15
 * Author: ChenYangQi
 * Description:错误状态
 */
public interface IErrorState {

    View getView(Context context);

    void setRetryClickListener(OnRetryClickListener retryClickListener);

    interface OnRetryClickListener {
        void onRetryClicked();
    }
}
