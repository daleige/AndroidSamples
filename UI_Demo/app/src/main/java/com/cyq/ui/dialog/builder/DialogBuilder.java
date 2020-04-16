package com.cyq.ui.dialog.builder;

import android.content.Context;

import com.cyq.ui.dialog.action.ActionListener;
import com.cyq.ui.dialog.dialog.BaseDialog;
import com.cyq.ui.dialog.dialog.MessageDialog;

/**
 * @author : ChenYangQi
 * date   : 2020/4/15 14:44
 * desc   : 各种类型的 Builder都应该继承DialogBuilder，该类定义一些通用的方法和规范
 */
public abstract class DialogBuilder {
    private Context mContext;
    protected MessageDialog mDialog;

    public DialogBuilder(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 设置标题
     */
    public abstract DialogBuilder setTitle(String title);

    /**
     * 设置dialog内容
     *
     * @param message
     */
    public abstract DialogBuilder setMessage(String message);

    /**
     * 点击外部是否关闭dialog
     *
     * @param b
     */
    public abstract DialogBuilder setCanceledOnTouchOutside(boolean b);

    /**
     * 取消点击事件回调
     *
     * @param action
     * @return
     */
    public abstract DialogBuilder setNegativeAction(String txt, ActionListener action);

    /**
     * 确认的点击事件
     *
     * @param txt
     * @param actionListener
     * @return
     */
    public abstract DialogBuilder setPositiveAction(String txt, ActionListener actionListener);

    /**
     * 生成一个dialog，但不显示
     *
     * @return
     */
    public abstract BaseDialog create();

    /**
     * 根据style生成dialog，但不显示
     *
     * @param styleId
     * @return
     */
    public abstract BaseDialog create(int styleId);

    /**
     * 生成一个Dialog，并显示
     */
    public abstract BaseDialog show();
}
