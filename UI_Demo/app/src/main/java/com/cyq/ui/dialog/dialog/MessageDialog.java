package com.cyq.ui.dialog.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.cyq.ui.R;

/**
 * @author : ChenYangQi
 * date   : 2020/4/15 15:04
 * desc   : 普通消息类型的Dialog
 */
public class MessageDialog extends BaseDialog {
    private Context mContext;
    private View rootView;
    private TextView mTvMessageTitle;
    private TextView mTvMessageContent;
    private TextView mTvSure;
    private TextView mTvCancel;

    public MessageDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_message;
    }

    @Override
    protected void init(Context context, View view) {
        mTvMessageTitle = view.findViewById(R.id.tv_message_title);
        mTvMessageContent = view.findViewById(R.id.tv_message_content);
        mTvSure = findViewById(R.id.tv_sure);
        mTvSure = findViewById(R.id.tv_cancel);
    }

    @Override
    public void setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            mTvMessageTitle.setText(title);
        }
    }

    @Override
    public void setMessage(String message) {
        if (!TextUtils.isEmpty(message)) {
            mTvMessageContent.setText(message);
        }
    }
}
