package com.cyq.graphchatview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : ChenYangQi
 * date   : 2019/12/11 14:04
 * desc   :
 */
public class GraphChatView extends View {
    private int tvColor = Color.parseColor("#626262");
    private int tvSize = 17;
    private float lineWidth = 0.5f;
    private int lineColor = Color.parseColor("#626262");
    private float lineMarginLeft = 7;
    private float lineMarginBottom = 10;
    private int graphColor = Color.parseColor("#FF5500");
    private float graphWidth = 3.88f;

    private Paint tvPaint;
    private Paint linePaint;
    private Paint graphPaint;

    private List<String> xRoller = new ArrayList<>();
    private List<String> yRoller = new ArrayList<>();
    private List<TempBean> tempList = new ArrayList<>();

    public GraphChatView(Context context) {
        this(context, null);
    }

    public GraphChatView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GraphChatView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        tvSize = sp2px(tvSize);
        lineWidth = dip2px(getContext(), lineWidth);
        lineMarginLeft = dip2px(getContext(), lineMarginLeft);
        lineMarginBottom = dip2px(getContext(), lineMarginBottom);
        graphWidth = dip2px(getContext(), graphWidth);

        tvPaint = new Paint();
        tvPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        tvPaint.setAntiAlias(true);
        tvPaint.setColor(tvColor);
        tvPaint.setTextSize(tvSize);

        linePaint = new Paint();
        linePaint.setStrokeWidth(lineWidth);
        linePaint.setColor(lineColor);
        linePaint.setAntiAlias(true);

        graphPaint = new Paint();
        graphPaint.setColor(graphColor);
        graphPaint.setAntiAlias(true);
        graphPaint.setStrokeWidth(graphWidth);
        graphPaint.setStyle(Paint.Style.STROKE);

        yRoller.add("0");
        yRoller.add("50");
        yRoller.add("100");
        yRoller.add("150");
        yRoller.add("200");
        yRoller.add("250");

        TempBean tempBean1 = new TempBean();
        tempBean1.setTimestamp(1575993600);
        tempBean1.setTemp(20);
        TempBean tempBean2 = new TempBean();
        tempBean2.setTimestamp(1575994500); //15
        tempBean2.setTemp(200);
        TempBean tempBean3 = new TempBean();
        tempBean3.setTimestamp(1575994800); //20
        tempBean3.setTemp(210);
        TempBean tempBean4 = new TempBean();
        tempBean4.setTimestamp(1575994920); //22
        tempBean4.setTemp(218);
        TempBean tempBean5 = new TempBean();
        tempBean5.setTimestamp(1575995400);//30
        tempBean5.setTemp(260);
        TempBean tempBean6 = new TempBean();
        tempBean6.setTimestamp(1575995700);//35
        tempBean6.setTemp(260);
        TempBean tempBean7 = new TempBean();
        tempBean7.setTimestamp(1575995880);//38
        tempBean7.setTemp(260);

        tempList.add(tempBean1);
        tempList.add(tempBean2);
        tempList.add(tempBean3);
        tempList.add(tempBean4);
        tempList.add(tempBean5);
        tempList.add(tempBean6);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        TimeUtils.XRollerInfo infoList = TimeUtils.getXRollerInfo(tempList);
        if (infoList != null) {
            xRoller.clear();
            xRoller.add(infoList.firstStr);
            xRoller.add(infoList.secondStr);
            xRoller.add(infoList.threeStr);
            xRoller.add(infoList.typeStr);
        }

        String baseLengthStr = "a";
        for (String str : yRoller) {
            if (str.length() > baseLengthStr.length()) {
                baseLengthStr = str;
            }
        }

        Rect yTvBounds = new Rect();
        tvPaint.getTextBounds(baseLengthStr, 0, baseLengthStr.length(), yTvBounds);
        int yTvWidth = yTvBounds.width();
        int yTvHeight = yTvBounds.height();
        int yItemHeight = (int) ((getHeight() - yTvHeight - lineMarginBottom) / yRoller.size());

        for (int i = 0; i < yRoller.size(); i++) {
            //画Y轴文字
            String text = yRoller.get(yRoller.size() - i - 1);
            Rect textBounds = new Rect();
            tvPaint.getTextBounds(text, 0, text.length(), textBounds);
            int topY = yItemHeight * i + yItemHeight - yTvHeight;
            int baseLine = measureBaseLine(tvPaint, text, topY);
            canvas.drawText(text, (yTvWidth - textBounds.width()) / 2, baseLine, tvPaint);
            //画横线
            canvas.drawLine(yTvWidth + lineMarginLeft, topY + yTvHeight / 2, getWidth(), topY + yTvHeight / 2, linePaint);
        }

        for (int i = 0; i < xRoller.size(); i++) {
            float xItemWidth = (getWidth() - yTvWidth - lineMarginLeft) / (xRoller.size() - 1);
            float xIndex = (yTvWidth + lineMarginLeft + xItemWidth * i);
            //画X轴文字
            String text = xRoller.get(i);
            Rect textBounds = new Rect();
            tvPaint.getTextBounds(text, 0, text.length(), textBounds);
            //TODO 这个地方的topY有2像素左右误差。待处理
            int baseLine = measureBaseLine(tvPaint, text, getHeight() - textBounds.height() - 2);
            canvas.drawText(text, xIndex - textBounds.width(), baseLine, tvPaint);
        }

        //画曲线
        int originX = (int) (yTvWidth + lineMarginLeft);
        int originY = (int) (getHeight() - ((3 * yTvHeight) >> 1) - lineMarginBottom - lineWidth);
        canvas.translate(originX, originY);
        canvas.save();
        //换算成对应的xy轴坐标

        //最开始的一个点的时间
        float timestampStart = tempList.get(0).getTimestamp();
        //最后一个点的时间
        float timeStampEnd = tempList.get(tempList.size() - 1).getTimestamp();
        //点的时间差，单位=秒
        float timeDifference = timeStampEnd - timestampStart;
        //x轴代表的总时长，单位=秒
        float xTotleTime = infoList.getMaxMillis() * 60;
        float scale = timeDifference / xTotleTime;

        mPointList.clear();
        for (int i = 0; i < tempList.size(); i++) {
            int temp = tempList.get(i).getTemp();
            float timestamp = tempList.get(i).getTimestamp();
            int y = temp * (originY + yTvHeight / 2) / 300;
            int x = (int) ((timestamp - timestampStart) * (getWidth() - originX) * scale / (timeStampEnd - timestampStart));
            Point point = new Point(x, -y);
            mPointList.add(point);
        }
        measurePath();
        Path dst = new Path();
        dst.rLineTo(0, 0);
        float distance = mPathMeasure.getLength() * drawScale;
        if (mPathMeasure.getSegment(0, distance, dst, true)) {
            //绘制线
            canvas.drawPath(dst, graphPaint);
        }
    }

    private float lineSmoothness = 0.2f;
    private List<Point> mPointList = new ArrayList<>();
    private Path mPath;
    private Path mAssistPath;
    private float drawScale = 1f;
    private PathMeasure mPathMeasure;

    private void measurePath() {
        mPath = new Path();
        mAssistPath = new Path();
        float prePreviousPointX = Float.NaN;
        float prePreviousPointY = Float.NaN;
        float previousPointX = Float.NaN;
        float previousPointY = Float.NaN;
        float currentPointX = Float.NaN;
        float currentPointY = Float.NaN;
        float nextPointX;
        float nextPointY;

        final int lineSize = mPointList.size();
        for (int valueIndex = 0; valueIndex < lineSize; ++valueIndex) {
            if (Float.isNaN(currentPointX)) {
                Point point = mPointList.get(valueIndex);
                currentPointX = point.x;
                currentPointY = point.y;
            }
            if (Float.isNaN(previousPointX)) {
                //是否是第一个点
                if (valueIndex > 0) {
                    Point point = mPointList.get(valueIndex - 1);
                    previousPointX = point.x;
                    previousPointY = point.y;
                } else {
                    //是的话就用当前点表示上一个点
                    previousPointX = currentPointX;
                    previousPointY = currentPointY;
                }
            }

            if (Float.isNaN(prePreviousPointX)) {
                //是否是前两个点
                if (valueIndex > 1) {
                    Point point = mPointList.get(valueIndex - 2);
                    prePreviousPointX = point.x;
                    prePreviousPointY = point.y;
                } else {
                    //是的话就用当前点表示上上个点
                    prePreviousPointX = previousPointX;
                    prePreviousPointY = previousPointY;
                }
            }

            // 判断是不是最后一个点了
            if (valueIndex < lineSize - 1) {
                Point point = mPointList.get(valueIndex + 1);
                nextPointX = point.x;
                nextPointY = point.y;
            } else {
                //是的话就用当前点表示下一个点
                nextPointX = currentPointX;
                nextPointY = currentPointY;
            }

            if (valueIndex == 0) {
                // 将Path移动到开始点
                mPath.moveTo(currentPointX, currentPointY);
                mAssistPath.moveTo(currentPointX, currentPointY);
            } else {
                // 求出控制点坐标
                final float firstDiffX = (currentPointX - prePreviousPointX);
                final float firstDiffY = (currentPointY - prePreviousPointY);
                final float secondDiffX = (nextPointX - previousPointX);
                final float secondDiffY = (nextPointY - previousPointY);
                final float firstControlPointX = previousPointX + (lineSmoothness * firstDiffX);
                final float firstControlPointY = previousPointY + (lineSmoothness * firstDiffY);
                final float secondControlPointX = currentPointX - (lineSmoothness * secondDiffX);
                final float secondControlPointY = currentPointY - (lineSmoothness * secondDiffY);
                //画出曲线
                mPath.cubicTo(firstControlPointX, firstControlPointY, secondControlPointX,
                        secondControlPointY,
                        currentPointX, currentPointY);
                //将控制点保存到辅助路径上
                mAssistPath.lineTo(firstControlPointX, firstControlPointY);
                mAssistPath.lineTo(secondControlPointX, secondControlPointY);
                mAssistPath.lineTo(currentPointX, currentPointY);
            }

            // 更新值,
            prePreviousPointX = previousPointX;
            prePreviousPointY = previousPointY;
            previousPointX = currentPointX;
            previousPointY = currentPointY;
            currentPointX = nextPointX;
            currentPointY = nextPointY;
        }
        mPathMeasure = new PathMeasure(mPath, false);
    }

    /**
     * 计算文字基线
     *
     * @param textPaint
     * @param text
     * @param topY      文字Y轴顶部坐标
     * @return
     */
    public static int measureBaseLine(Paint textPaint, String text, int topY) {
        Rect textBounds = new Rect();
        textPaint.getTextBounds(text, 0, text.length(), textBounds);
        Paint.FontMetricsInt fontMetricsInt = textPaint.getFontMetricsInt();
        int dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
        return topY + (textBounds.height() / 2) + dy;
    }

    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
