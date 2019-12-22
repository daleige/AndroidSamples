package com.cyq.expendtextview;

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
    public MyExpendTextView(Context context) {
        this(context, null);
    }

    public MyExpendTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyExpendTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置当前最大展示行数
     */
    public void setExpend(boolean isExpend) {
        if (isExpend) {
            //展开
            setMaxLines(30);
        } else {
            //收缩
            setMaxLines(3);
        }
    }

}
