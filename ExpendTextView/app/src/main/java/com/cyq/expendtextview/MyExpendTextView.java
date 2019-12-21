package com.cyq.expendtextview;

import android.content.Context;
import android.text.TextUtils;
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
    private int maxLines = 100;
    /**
     * 控件展开缩进状态 false=缩进，true=展开
     */
    public boolean mTagger = false;

    public MyExpendTextView(Context context) {
        this(context, null);
    }

    public MyExpendTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyExpendTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //文本过长末尾展示...
        setEllipsize(TextUtils.TruncateAt.END);
    }

    /**
     * 设置当前最大展示行数
     */
    public void setCurrentLines() {
        if (mTagger) {
            setMaxLines(maxLines);
            mTagger = false;
        } else {
            setMaxLines(minLines);
            mTagger = true;
        }
    }

    public boolean isTagger() {
        return mTagger;
    }

    /**
     * 初始化状态
     *
     * @param count
     */
    public void setInitLines(int count) {
        setMaxLines(count);
    }
}
