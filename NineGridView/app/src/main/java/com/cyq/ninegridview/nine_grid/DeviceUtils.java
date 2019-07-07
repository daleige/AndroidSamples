package com.cyq.ninegridview.nine_grid;

import android.content.Context;

/**
 * Create by 陈扬齐
 * Create on 2019-07-07
 * description:
 */
public class DeviceUtils {

    /**
     * dp to px
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
