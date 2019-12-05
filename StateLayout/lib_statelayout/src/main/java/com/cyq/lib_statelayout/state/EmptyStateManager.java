package com.cyq.lib_statelayout.state;

import android.content.Context;
import android.view.View;

import com.cyq.lib_statelayout.R;
import com.cyq.lib_statelayout.interfaces.IEmptyState;

/**
 * @author : ChenYangQi
 * date   : 2019/12/5 9:57
 * desc   :
 */
public class EmptyStateManager implements IEmptyState {
    private View mView;

    @Override
    public View getView(Context context) {
        if (mView == null) {
            mView = View.inflate(context, R.layout.empty_state_layout, null);
        }
        return mView;
    }
}
