package com.cyq.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author : ChenYangQi
 * date   : 2019/12/13 19:43
 * desc   : 雷达图
 */
public class RadarView extends View {

    private Paint radarPaint;
    private Paint valuePaint;
    private float mRadius;
    private float mCenterX;
    private float mCenterY;
    private int mLevel = 6;

    public RadarView(Context context) {
        this(context, null);
    }

    public RadarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        radarPaint = new Paint();
        radarPaint.setAntiAlias(true);
        radarPaint.setStyle(Paint.Style.STROKE);
        radarPaint.setStrokeWidth(10);
        radarPaint.setColor(Color.BLACK);

        valuePaint = new Paint();
        valuePaint.setAntiAlias(true);
        valuePaint.setStyle(Paint.Style.STROKE);
        valuePaint.setStrokeWidth(6);
        valuePaint.setColor(Color.GREEN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制蜘蛛网格
        drawPolygon(canvas);
        //画网格中线
        drawLines(canvas);
        //画数据图
        drawRegion(canvas);
    }

    /**
     * 绘制蜘蛛网格
     *
     * @param canvas
     */
    private void drawPolygon(Canvas canvas) {
        mRadius = Math.min(getWidth(), getHeight()) / 2;
        mCenterX = getWidth() / 2;
        mCenterY = getHeight() / 2;
        canvas.translate(mCenterX, mCenterY);
        Path path = new Path();
        float r = mRadius / mLevel;
        for (int i = 1; i <= mLevel; i++) {
            float curR = r * i;
            path.reset();
            for (int j = 0; j < i; j++) {
                if (j == 0) {
                    path.moveTo(curR, 0);
                } else {
                    //根据半径，计算出蜘蛛丝上每个点的坐标
                    float x = (float) (curR * Math.cos(60 * j));
                    float y = (float) (curR * Math.sin(60 * j));
                    path.lineTo(x, y);
                    Log.i("test", "x:" + x + "----y:" + y);
                }
            }
            path.close();//闭合路径
            canvas.drawPath(path, radarPaint);
        }
    }

    /**
     * 画网格中线
     *
     * @param canvas
     */
    private void drawLines(Canvas canvas) {
    }

    /**
     * 画数据图
     *
     * @param canvas
     */
    private void drawRegion(Canvas canvas) {
    }
}
