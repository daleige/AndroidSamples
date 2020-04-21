package com.cyq.ui.dialog.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cyq.ui.R;
import com.cyq.ui.dialog.action.ActionListener;
import com.cyq.ui.dialog.dialog.MyDialog;

/**
 * @author : ChenYangQi
 * date   : 2020/4/21 14:35
 * desc   : Dialog控件，用于限定常用操作，提供外部统一访问方法
 */
public class DialogView extends RelativeLayout implements IDialogViewInterface {
    private LinearLayout mllActionContainer;
    private TextView mTvTitle;
    private TextView mTvSure;
    private TextView mTvCancel;
    private FrameLayout mFlContent;

    public DialogView(Context context) {
        super(context);
        init();
    }

    public DialogView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DialogView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.dialog_message, this, true);
        mllActionContainer = findViewById(R.id.ll_message_action_container);
        mTvTitle = findViewById(R.id.tv_message_title);
        mTvSure = findViewById(R.id.tv_sure);
        mTvCancel = findViewById(R.id.tv_cancel);
        mFlContent = findViewById(R.id.fl_dialog_content);
    }

    @Override
    public void setContentView(View contentView) {
        mFlContent.removeAllViews();
        mFlContent.addView(contentView);
    }

    @Override
    public void setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            mTvTitle.setText(title);
        }
    }

    @Override
    public void setPositiveAction(final MyDialog dialog, String txt, final ActionListener action) {
        mTvSure.setText(txt);
        mTvSure.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (action != null) {
                    action.onClick(dialog, 1);
                }
            }
        });
    }

    @Override
    public void setNegativeAction(final MyDialog dialog, String txt, final ActionListener action) {
        mTvCancel.setText(txt);
        mTvCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (action != null) {
                    action.onClick(dialog, 0);
                }
            }
        });
    }
}
