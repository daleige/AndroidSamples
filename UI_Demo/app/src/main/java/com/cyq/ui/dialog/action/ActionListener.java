package com.cyq.ui.dialog.action;

import com.cyq.ui.dialog.dialog.BaseDialog;

/**
 * @author : ChenYangQi
 * date   : 2020/4/16 9:33
 * desc   : 点击事件回调
 */
public interface ActionListener {

    /**
     * 定义响应点击事件的回调
     *
     * @param dialog dialog
     * @param index 点击的位置下标，
     */
    public void onClick(BaseDialog dialog, int index);
}
