package com.cyq.ui.dialog.builder;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.cyq.ui.R;
import com.cyq.ui.dialog.action.ActionListener;
import com.cyq.ui.dialog.dialog.MyDialog;
import com.cyq.ui.dialog.util.DialogView;

/**
 * @author : ChenYangQi
 * date   : 2020/4/15 14:44
 * desc   : 各种类型的 Builder都应该继承DialogBuilder，该类定义一些通用的方法和规范
 */
public abstract class DialogBuilder<T extends DialogBuilder> {
    protected Context mContext;
    protected MyDialog mDialog;
    protected String mTitleStr;
    protected String mSureStr;
    protected String mCancelStr;
    protected String mContentStr;
    protected ActionListener mSureActionListener;
    protected ActionListener mCancelActionListener;
    protected boolean mCancelOnTouchOutside = true;

    public DialogBuilder(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 由子类负责实现创建具体的Dialog内容
     *
     * @param dialog
     * @param context
     * @return
     */
    protected abstract View onCreateContent(@NonNull MyDialog dialog, @NonNull Context context);

    public T setTitle(String title) {
        this.mTitleStr = title;
        return (T) this;
    }

    public T setMessage(String message) {
        this.mContentStr = message;
        return (T) this;
    }



    public T setCanceledOnTouchOutside(boolean b) {
        this.mCancelOnTouchOutside = b;
        return (T) this;
    }

    public T setNegativeAction(String txt, ActionListener action) {
        this.mCancelActionListener = action;
        this.mCancelStr = txt;
        return (T) this;
    }

    public T setPositiveAction(String txt, ActionListener action) {
        this.mSureActionListener = action;
        this.mSureStr = txt;
        return (T) this;
    }

    /**
     * 创建Dialog，但不显示
     *
     * @return
     */
    public MyDialog create() {
        mDialog = new MyDialog(mContext);
        View contentView = onCreateContent(mDialog, mContext);
        DialogView dialogView = new DialogView(mContext);
        if (!TextUtils.isEmpty(mTitleStr)) {
            dialogView.setTitle(mTitleStr);
        }
        if (!TextUtils.isEmpty(mSureStr)) {
            dialogView.setPositiveAction(mDialog, mSureStr, new ActionListener() {
                @Override
                public void onClick(MyDialog dialog, int index) {

                }
            });
        }
        if (!TextUtils.isEmpty(mCancelStr)) {
            dialogView.setNegativeAction(mDialog, mCancelStr, new ActionListener() {
                @Override
                public void onClick(MyDialog dialog, int index) {

                }
            });
        }
        if (contentView != null) {
            dialogView.setContentView(contentView);
        }
        mDialog.setContentView(dialogView);
        return mDialog;
    }

    /**
     * 创建Dialog，并且显示
     *
     * @return
     */
    public MyDialog show() {
        mDialog = create();
        mDialog.show();
        return mDialog;
    }

    /**
     * 设置动画
     */
    protected void setAnimStyle(int animStyleId) {
        Window window = mDialog.getWindow();
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
        Window dialogWindow = mDialog.getWindow();
        if (dialogWindow != null) {
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            dialogWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
            dialogWindow.setGravity(gravity);
            dialogWindow.setAttributes(lp);
        }
    }
}
