package com.cyq.expendtextview.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * @author : ChenYangQi
 * date   : 2019/12/21 8:39
 * desc   : 文字展开收缩控件，支持展开隐藏的动画
 */
public class MyExpendTextView extends AppCompatTextView {
    private int minLines = 3;

    public MyExpendTextView(Context context) {
        this(context, null);
    }

    public MyExpendTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyExpendTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
//        Log.i("test","height:" + height);
    }

    /**
     * 设置当前最大展示行数
     */
    public void setExpend(boolean isExpend) {
        if (isExpend) {
            //展开
            setMaxLines(Integer.MAX_VALUE);
        } else {
            //收缩
            setMaxLines(3);
        }
    }


    private int dp2px(Context context, float dpValue) {
        int res = 0;
        final float scale = context.getResources().getDisplayMetrics().density;
        if (dpValue < 0)
            res = -(int) (-dpValue * scale + 0.5f);
        else
            res = (int) (dpValue * scale + 0.5f);
        return res;
    }
}
