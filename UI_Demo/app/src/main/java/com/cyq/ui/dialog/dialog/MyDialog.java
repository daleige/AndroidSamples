package com.cyq.ui.dialog.dialog;

import android.content.Context;

import com.cyq.ui.dialog.action.ActionListener;
import com.cyq.ui.dialog.builder.DialogBuilder;

/**
 * @author : ChenYangQi
 * date   : 2020/4/15 14:37
 * desc   :
 */
public class MyDialog {

    private Context mContext;

    public MyDialog(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 消息类型的对话框 Builder。通过它可以生成一个带标题、文本消息、按钮的对话框。
     */
    public static class MessageDialogBuilder extends DialogBuilder {

        public MessageDialogBuilder(Context mContext) {
            super(mContext);
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
}
