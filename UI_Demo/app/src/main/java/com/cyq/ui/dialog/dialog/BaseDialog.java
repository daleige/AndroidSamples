package com.cyq.ui.dialog.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.cyq.ui.R;

/**
 * @author : ChenYangQi
 * date   : 2020/4/15 15:40
 * desc   : 基类Activity
 */
public abstract class BaseDialog extends Dialog {

    public BaseDialog(Context context) {
        super(context, R.style.BaseDialog);
        if (getContentView() == null) {
            setContentView(getContentId());
        } else {
            setContentView(getContentView());
        }
        init(context);
    }

    /**
     * 布局资源
     *
     * @return 返回布局id
     */
    protected abstract int getContentId();

    /**
     * 初始化方法
     */
    protected abstract void init(Context context);

    /**
     * 布局View 返回null 时 getContentId 起作用
     *
     * @return null
     */
    private View getContentView() {
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
}
