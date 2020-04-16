package com.cyq.ui.dialog.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.cyq.ui.R;
import com.cyq.ui.dialog.action.ActionListener;
import com.cyq.ui.dialog.builder.DialogBuilder;

/**
 * @author : ChenYangQi
 * date   : 2020/4/15 15:40
 * desc   : 基类Activity
 */
public abstract class BaseDialog extends Dialog {

    public BaseDialog(Context context) {
        super(context, R.style.BaseDialog);
        View view = LayoutInflater.from(context).inflate(getLayoutId(), null, false);
        setContentView(view);
        init(context, view);
    }

    /**
     * 布局资源
     *
     * @return 返回布局id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化方法
     */
    protected abstract void init(Context context, View view);

    /**
     * 布局View 返回null 时 getContentId 起作用
     *
     * @return null
     */
    protected View getContentView() {
        return null;
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

    /**
     * 设置标题
     */
    public abstract void setTitle(String title);

    /**
     * 设置dialog内容
     *
     * @param message
     */
    public abstract void setMessage(String message);

    /**
     * 设置取消按钮的文字
     *
     * @param txt
     */
    public abstract void setNegativeTxt(String txt, ActionListener actionListener);

    /**
     * 设置确认按钮的文字
     *
     * @param txt
     */
    public abstract void setPositiveTxt(String txt,ActionListener actionListener);

}
