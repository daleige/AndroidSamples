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
    private int tvColor = Color.parseColor("#000000");
    private int tvSize = 17;
    private int lineWidth = 1;
    private int lineColor = Color.parseColor("#000000");
    private int lineMarginLeft = 7;
    private int lineMarginBottom = 10;

    private Paint tvPaint;
    private Paint linePaint;

    private List<String> xRoller = new ArrayList<>();
    private List<String> yRoller = new ArrayList<>();

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
        lineWidth = dip2px(lineWidth);
        lineMarginLeft = dip2px(lineMarginLeft);
        lineMarginBottom = dip2px(lineMarginBottom);

        tvPaint = new Paint();
        tvPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        tvPaint.setAntiAlias(true);
        tvPaint.setColor(tvColor);
        tvPaint.setTextSize(tvSize);

        linePaint = new Paint();
        linePaint.setColor(tvColor);
        linePaint.setStrokeWidth(lineWidth);
        linePaint.setColor(lineColor);
        linePaint.setAntiAlias(true);

        yRoller.add("0");
        yRoller.add("50");
        yRoller.add("100");
        yRoller.add("150");
        yRoller.add("250");
        yRoller.add("300");

        xRoller.add("10");
        xRoller.add("20");
        xRoller.add("30");
        xRoller.add("40");
        xRoller.add("50");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect yTvBounds = new Rect();
        tvPaint.getTextBounds("888", 0, "888".length(), yTvBounds);
        int yTvWidth = yTvBounds.width();
        int yTvHeight = yTvBounds.height();
        int yItemHeight = (getHeight() - yTvHeight) / yRoller.size();

        for (int i = 0; i < yRoller.size(); i++) {
            //画Y轴文字
            String text = yRoller.get(yRoller.size() - i - 1);
            Rect textBounds = new Rect();
            tvPaint.getTextBounds(text, 0, text.length(), textBounds);
            int baseLine = measureBaseLine(tvPaint, text, yItemHeight * i);
            canvas.drawText(text, (yTvWidth - textBounds.width()) / 2, baseLine, tvPaint);

            //画横线
            canvas.drawLine(yTvWidth + lineMarginLeft, yItemHeight * i + yTvHeight / 2, getWidth(), yItemHeight * i + yTvHeight / 2, linePaint);
        }

        for (int i = 0; i < xRoller.size(); i++) {
            int xItemWidth = (getWidth() - yTvWidth - lineMarginLeft) / xRoller.size();
            int xIndexMid = yTvWidth + lineMarginLeft + xItemWidth * i + xItemWidth / 2;
            Log.i("test", "-------------" + xIndexMid + "-----" + getWidth());

            //画Y轴文字
            String text = xRoller.get(i);
            Rect textBounds = new Rect();

            tvPaint.getTextBounds(text, 0, text.length(), textBounds);
            int baseLine = measureBaseLine(tvPaint, text, getHeight() - yTvHeight);
            canvas.drawText(text, xIndexMid - textBounds.width() / 2, baseLine, tvPaint);
        }
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

    private int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
