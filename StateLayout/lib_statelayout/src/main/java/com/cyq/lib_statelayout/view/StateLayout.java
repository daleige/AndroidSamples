package com.cyq.lib_statelayout.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Time: 2019-12-01 22:22
 * Author: ChenYangQi
 * Description:
 */
public class StateLayout extends FrameLayout {
    public StateLayout(@NonNull Context context) {
        super(context);
    }

    public StateLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StateLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
