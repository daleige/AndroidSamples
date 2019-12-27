package com.cyq.expendtextview.view;

import android.content.Context;
import android.os.Build;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

/**
 * @author : ChenYangQi
 * date   : 2019/12/27 14:15
 * desc   :
 */
public class Utils {
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static int getTotalLineHeight(TextView textView, String content, int width) {
        if (TextUtils.isEmpty(content)) {
            return 0;
        }
        TextPaint textPaint = textView.getPaint();
//      StaticLayout staticLayout = new StaticLayout(content, textPaint, width, Layout.Alignment.ALIGN_NORMAL,
//                1, UIUtil.dip2Px(CONTENT_LINE_SPACING), true);
//      return staticLayout.getHeight();
        StaticLayout staticLayout = StaticLayout.Builder
                .obtain(content, 0, content.length(), textPaint, width)
                .build();
        return staticLayout.getHeight();
    }

    public static int dp2px(Context context, float dpValue) {
        int res = 0;
        final float scale = context.getResources().getDisplayMetrics().density;
        if (dpValue < 0) {
            res = -(int) (-dpValue * scale + 0.5f);
        } else {
            res = (int) (dpValue * scale + 0.5f);
        }
        return res;
    }
}
