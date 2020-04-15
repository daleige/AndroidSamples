package com.cyq.ui.dialog.dialog;

import android.content.Context;

/**
 * @author : ChenYangQi
 * date   : 2020/4/15 15:04
 * desc   :
 */
public class MessageDialog extends BaseDialog {


    public MessageDialog(Context context) {
        super(context);
    }

    @Override
    protected int getContentId() {
        return 0;
    }

    @Override
    protected void init(Context context) {

    }
}
