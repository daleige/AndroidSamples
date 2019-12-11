package com.cyq.graphchatview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
        graphPaint.setStrokeWidth(graphWidth);
        graphPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        yRoller.add("0");
        yRoller.add("50");
        yRoller.add("100");
        yRoller.add("150");
        yRoller.add("250");

        xRoller.add("0");
        xRoller.add("15");
        xRoller.add("30");
        xRoller.add("分");

        TempBean tempBean1 = new TempBean();
        tempBean1.setTimestamp(1575993600);
        tempBean1.setTemp(12);
        TempBean tempBean2 = new TempBean();
        tempBean2.setTimestamp(1575994360); //12
        tempBean2.setTemp(60);
        TempBean tempBean3 = new TempBean();
        tempBean3.setTimestamp(1575994594); //16
        tempBean3.setTemp(80);
        TempBean tempBean4 = new TempBean();
        tempBean4.setTimestamp(1575994834); //20
        tempBean4.setTemp(190);
        TempBean tempBean5 = new TempBean();
        tempBean5.setTimestamp(1575995194);//26
        tempBean5.setTemp(220);
        TempBean tempBean6 = new TempBean();
        tempBean6.setTimestamp(1575995400);//30
        tempBean6.setTemp(260);

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
            int xItemWidth = (int) ((getWidth() - yTvWidth - lineMarginLeft) / xRoller.size());
            int xIndexMid = (int) (yTvWidth + lineMarginLeft + xItemWidth * i + xItemWidth / 2);
            //画X轴文字
            String text = xRoller.get(i);
            Rect textBounds = new Rect();
            tvPaint.getTextBounds(text, 0, text.length(), textBounds);
            //TODO 这个地方的topY有2像素左右误差。待处理
            int baseLine = measureBaseLine(tvPaint, text, getHeight() - textBounds.height() - 2);
            canvas.drawText(text, xIndexMid - textBounds.width() / 2, baseLine, tvPaint);
        }

        //画曲线
        int originX = (int) (yTvWidth + lineMarginLeft);
        int originY = (int) (getHeight() - ((3 * yTvHeight) >> 1) - lineMarginBottom - lineWidth);
        canvas.translate(originX, originY);
        canvas.save();

        canvas.drawCircle(0, 0, 4, graphPaint);

        canvas.restore();
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
