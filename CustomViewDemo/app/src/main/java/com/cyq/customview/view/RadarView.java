package com.cyq.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
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
    private int mRadius;
    private int mCenterX;
    private int mCenterY;
    private int mLevel = 6;
    private int mFaceCount = 6;

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
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mRadius = Math.min(w, h);
        mCenterX = w / 2;
        mCenterY = h / 2;
        postInvalidate();
        super.onSizeChanged(w, h, oldw, oldh);
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
        Path path = new Path();
        float r = mRadius / (mLevel); //r 是蜘蛛丝之间的间距
        for (int i = 1; i <= mLevel; i++) { //中心点不用绘制
            float curR = r * i; //当前半径
            path.reset();
            for (int j = 0; j < mLevel; j++) {
                if (j == 0) {
                    path.moveTo(mCenterX + curR, mCenterY);
                } else {
                    int angle = 360 / 60 * j;
                    //根据半径，计算出蜘蛛丝上每个点的坐标
                    float x = (float) (mCenterX + curR * Math.cos(angle * j));
                    float y = (float) (mCenterY + curR * Math.sin(angle * j));
                    path.lineTo(x, y);
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
