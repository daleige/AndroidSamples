package com.cyq.lib_statelayout.interfaces;

import android.content.Context;
import android.view.View;

/**
 * Time: 2019-12-01 22:21
 * Author: ChenYangQi
 * Description:加载中状态
 */
public interface ILoadingState {
    View getView(Context context);

    void hide();

    void show();

}
