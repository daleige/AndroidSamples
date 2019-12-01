package com.cyq.lib_statelayout.state;

import android.content.Context;
import android.view.View;

import com.cyq.lib_statelayout.R;
import com.cyq.lib_statelayout.interfaces.ILoadingState;

/**
 * Time: 2019-12-01 22:45
 * Author: ChenYangQi
 * Description:加载中状态管理类
 */
public class LoadingManagerState implements ILoadingState {
    private View mView;

    @Override
    public View getView(Context context) {
        if (mView == null) {
            mView = View.inflate(context, R.layout.loading_state_layout, null);
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
}
