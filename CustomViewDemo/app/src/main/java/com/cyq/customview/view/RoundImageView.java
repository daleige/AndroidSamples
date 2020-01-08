package com.cyq.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * Time: 2020-01-07 23:43
 * Author: ChenYangQi
 * Description:圆角ImageView
 */
public class RoundImageView extends AppCompatImageView {
    private int radius = 30;
    private Path mPath;
    private RectF mRect;
    private int mViewWidth;
    private int mViewHeight;
    private Paint mPaint;

    public RoundImageView(Context context) {
        this(context, null);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPath = new Path();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mViewWidth = getMeasuredWidth();
        mViewHeight = getMeasuredHeight();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mRect = new RectF(0, 0, mViewWidth, mViewHeight);


        canvas.drawPath(mPath, mPaint);
    }
}
