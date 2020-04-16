package com.cyq.ui.dialog.builder;

import android.content.Context;

import com.cyq.ui.dialog.action.ActionListener;
import com.cyq.ui.dialog.dialog.BaseDialog;
import com.cyq.ui.dialog.dialog.MessageDialog;

/**
 * @author : ChenYangQi
 * date   : 2020/4/15 15:00
 * desc   : 消息类型的Dialog建造者
 */
public class MessageDialogBuilder extends DialogBuilder {
    private Context mContext;
    private MessageDialog mDialog;

    public MessageDialogBuilder(Context mContext) {
        this.mContext = mContext;
        this.mDialog = new MessageDialog(mContext);
    }


    @Override
    public MessageDialogBuilder setTitle(String title) {
        mDialog.setTitle(title);
        return this;
    }

    @Override
    public MessageDialogBuilder setMessage(String message) {
        mDialog.setMessage(message);
        return this;
    }

    @Override
    public MessageDialogBuilder setCanceledOnTouchOutside(boolean b) {
        mDialog.setCanceledOnTouchOutside(b);
        return this;
    }

    @Override
    public DialogBuilder setNegativeAction(String txt, ActionListener action) {
        mDialog.setNegativeTxt(txt, action);
        return this;
    }

    @Override
    public DialogBuilder setPositiveAction(String txt, ActionListener action) {
        mDialog.setPositiveTxt(txt, action);
        return this;
    }


    @Override
    public BaseDialog create() {
        return mDialog;
    }

    @Override
    public BaseDialog create(int styleId) {

        return mDialog;
    }

    @Override
    public BaseDialog show() {
        if (mDialog != null && !mDialog.isShowing()) {
            mDialog.show();
        }
        return mDialog;
    }
}
