package com.cyq.progressview.widget;

import android.content.Context;

/**
 * @author : ChenYangQi
 * date   : 2020/5/6 14:35
 * desc   :
 */
public class Utils {

    /**
     * dp 转 px
     * @param dpValue
     * @param context
     * @return
     */
    public static int dip2px(float dpValue, Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
