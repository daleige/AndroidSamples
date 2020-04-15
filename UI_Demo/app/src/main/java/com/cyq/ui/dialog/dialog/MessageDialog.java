package com.cyq.ui.dialog.dialog;

import android.content.Context;
import android.widget.TextView;

import com.cyq.ui.R;

/**
 * @author : ChenYangQi
 * date   : 2020/4/15 15:04
 * desc   : 普通消息类型的Dialog
 */
public class MessageDialog extends BaseDialog {

    private TextView mTvMessageTitle;
    private TextView mTvMessageContent;
    private TextView mTvSure;
    private TextView mTvCancel;

    public MessageDialog(Context context) {
        super(context);
    }

    @Override
    protected int getContentId() {
        return R.layout.dialog_message;
    }

    @Override
    protected void init(Context context) {

    }
}
