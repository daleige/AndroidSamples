package com.wangyi.musicplayer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Time: 2020/6/2 10:12 PM
 * Author: ChenYangQi
 * Description:
 */
public class VisualizerView extends View {
    private Paint mPaint;
    private float width;
    private float height;
    private float[] mPts = new float[0];

    public VisualizerView(Context context) {
        this(context, null);
    }

    public VisualizerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VisualizerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5);

        width = getWidth();
        height = getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLines(mPts, mPaint);
    }

    int startX, startY, endX, endY;

    /**
     * 设置音频可视化数据
     */
    public void setVisualizer(byte[] data) {
        mPts = new float[data.length * 4];
        for (int i = 0; i < data.length; i++) {
            startX = endX = i * 10;
            startY = 0;
            endY = data[i];
            mPts[4 * i] = startX;
            mPts[4 * i + 1] = startY;
            mPts[4 * i + 2] = endX;
            mPts[4 * i + 3] = endY;
        }

        invalidate();
    }
}
