package com.cyq.ui.dialog.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDialog;

import com.cyq.ui.R;
import com.cyq.ui.dialog.action.ActionListener;

/**
 * @author : ChenYangQi
 * date   : 2020/4/15 15:40
 * desc   : 基类Activity
 */
public abstract class BaseDialog extends AppCompatDialog {
    private LinearLayout mllActionContainer;
    private TextView mTvTitle;
    private TextView mTvSure;
    private TextView mTvCancel;
    private RelativeLayout mRootView;
    /**
     * dialog内容容器，由各个子类通过复写
     */
    private FrameLayout mFlContent;

    public BaseDialog(Context context) {
        super(context, R.style.BaseDialog);
        View view = LayoutInflater.from(context).inflate(getLayoutId(), null, false);
        setContentView(view);
        init(context);
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
    protected void init(Context context) {
        mRootView = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.dialog_message, null, false);
        mllActionContainer = mRootView.findViewById(R.id.ll_message_action_container);
        mTvTitle = mRootView.findViewById(R.id.tv_message_title);
        mTvSure = mRootView.findViewById(R.id.tv_sure);
        mTvCancel = mRootView.findViewById(R.id.tv_cancel);
        mFlContent = mRootView.findViewById(R.id.fl_dialog_content);
    }


    /**
     * 定义各个子构建者创建具体的dialog内容部分
     *
     * @return
     */
    protected abstract View onCreateContent();

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
    public abstract void setPositiveTxt(String txt, ActionListener actionListener);

}
