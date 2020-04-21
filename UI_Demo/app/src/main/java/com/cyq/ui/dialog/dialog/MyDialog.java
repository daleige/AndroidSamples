package com.cyq.ui.dialog.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialog;

import com.cyq.ui.dialog.builder.DialogBuilder;

/**
 * @author : ChenYangQi
 * date   : 2020/4/15 15:40
 * desc   : 基类Activity
 */
public class MyDialog extends AppCompatDialog {

    public MyDialog(Context context) {
        super(context);
    }

    /**
     * 消息类型的对话框 Builder。通过它可以生成一个带标题、文本消息、按钮的对话框。
     */
    public static class MessageDialogBuilder extends DialogBuilder<MessageDialogBuilder> {

        public MessageDialogBuilder(Context mContext) {
            super(mContext);
        }

        /**
         * 消息类型dialog，内容部分为一个文本控件
         *
         * @param dialog
         * @param context
         * @return
         */
        @Override
        protected View onCreateContent(@NonNull MyDialog dialog, @NonNull Context context) {
            TextView tvContent = new TextView(context);
            tvContent.setTextSize(16);
            if (!TextUtils.isEmpty(mContentStr)) {
                tvContent.setText(mContentStr);
            }
            return tvContent;
        }
    }

    /**
     * 设置动画
     */
    protected void setAnimStyle(int animStyleId) {
        Window window = getWindow();
        if (window != null) {
            window.setWindowAnimations(animStyleId);
        }
    }

    /**
     * 设置弹出的位置
     *
     * @param gravity 布局位置
     */
    protected void setDialogGravity(int gravity) {
        Window dialogWindow = getWindow();
        if (dialogWindow != null) {
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            dialogWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
            dialogWindow.setGravity(gravity);
            dialogWindow.setAttributes(lp);
        }
    }
}
