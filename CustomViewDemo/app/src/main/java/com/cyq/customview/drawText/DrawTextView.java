package com.cyq.customview.drawText;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author : ChenYangQi
 * date   : 2020/1/9 16:56
 * desc   :
 */
public class DrawTextView extends View {

    private String text;
    private int topX;
    private int topY;
    private int baseLineY;
    private Paint mPaint;
    private Paint mPointPaint;
    private int centerY;
    private int centerX;

    public DrawTextView(Context context) {
        this(context, null);
    }

    public DrawTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        text = "Hello girl，你好";
        topX = 200;
        topY = 200;
        centerY = 500;
        centerX = 200;
        baseLineY = 0;
        mPaint = new Paint();
        mPaint.setTextSize(80);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLACK);
        mPaint.setTextAlign(Paint.Align.LEFT);

        mPointPaint = new Paint();
        mPointPaint.setColor(Color.RED);
        mPointPaint.setStrokeWidth(10);
        mPointPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画文字左上角的点
        canvas.drawPoint(topX, topY, mPointPaint);
        baseLineY = getBaseLineByTop(topY, mPaint);
        //画文字
        canvas.drawText(text, topX, baseLineY, mPaint);

        //画中点
        canvas.drawPoint(centerX, centerY, mPointPaint);
        //画基于中点的文字
        canvas.drawText(text, centerX, getBaseLineByCenter(centerY, mPaint), mPaint);
    }


    /**
     * 根据左上角坐标计算baseLine的Y坐标
     *
     * @param topY
     * @return
     */
    private int getBaseLineByTop(int topY, Paint textPaint) {
        Paint.FontMetricsInt fontMetricsInt = textPaint.getFontMetricsInt();
        return topY - fontMetricsInt.top;
    }

    /**
     * 根据文字终点的Y坐标位置计算baseLine的Y坐标
     *
     * @param centerY
     * @param textPaint
     * @return
     */
    private int getBaseLineByCenter(int centerY, Paint textPaint) {
        Paint.FontMetricsInt fontMetricsInt = textPaint.getFontMetricsInt();
        return centerY + (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
    }
}
