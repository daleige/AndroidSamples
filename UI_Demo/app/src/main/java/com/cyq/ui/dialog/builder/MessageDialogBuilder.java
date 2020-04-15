package com.cyq.ui.dialog.builder;

import android.content.Context;

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

        return this;
    }

    @Override
    public MessageDialogBuilder setMessage(String message) {

        return this;
    }

    @Override
    public MessageDialogBuilder setCanceledOnTouchOutside(boolean b) {

        return this;
    }

    @Override
    public DialogBuilder create() {
        return null;
    }

    @Override
    public DialogBuilder create(int styleId) {
        return null;
    }

    @Override
    public void show() {
        if (mDialog != null && !mDialog.isShowing()) {
            mDialog.show();
        }
    }
}
