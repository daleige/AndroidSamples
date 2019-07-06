package com.cyq.ninegridview.nine_grid;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Create by 陈扬齐
 * Create on 2019-07-07
 * description:宽高自适应ImageView
 */
public class AutoSizeImageView extends AppCompatImageView {
    public AutoSizeImageView(Context context) {
        super(context);
    }

    public AutoSizeImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoSizeImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Drawable drawable = getDrawable();
        if (null != drawable) {
            int width = MeasureSpec.getSize(widthMeasureSpec);
            int height =
                    (int) Math.ceil((float) width * (float) drawable.getIntrinsicHeight() / (float) drawable.getIntrinsicWidth());
            setMeasuredDimension(width, height);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
