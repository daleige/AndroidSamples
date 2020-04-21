package com.cyq.ui.dialog.util;

import android.view.View;

import com.cyq.ui.dialog.action.ActionListener;
import com.cyq.ui.dialog.dialog.MyDialog;

/**
 * @author : ChenYangQi
 * date   : 2020/4/21 14:37
 * desc   :
 */
public interface IDialogViewInterface {

    void setContentView(View contentView);

    void setTitle(String title);

    void setPositiveAction(MyDialog dialog, String txt, ActionListener action);

    void setNegativeAction(MyDialog dialog, String txt, ActionListener action);
}
