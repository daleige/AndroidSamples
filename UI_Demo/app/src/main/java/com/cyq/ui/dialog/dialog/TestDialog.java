package com.cyq.ui.dialog.dialog;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;

import com.cyq.ui.R;

/**
 * @author : ChenYangQi
 * date   : 2020/4/15 17:25
 * desc   :
 */
public class TestDialog extends Dialog {
    public TestDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_message);
    }
}
