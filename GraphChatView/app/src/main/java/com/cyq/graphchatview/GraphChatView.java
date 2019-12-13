package com.cyq.graphchatview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
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
    private float topPointWidth = 30f;
    private float lineSmoothness = 0.2f;

    private Paint tvPaint;
    private Paint linePaint;
    private Paint graphPaint;
    private Paint bitmapPaint;
    private List<Point> mPointList = new ArrayList<>();
    private Path mPath;
    private Path mAssistPath;
    private float drawScale = 1f;
    private PathMeasure mPathMeasure;
    private List<TempBean> tempList = new ArrayList<>();
    private List<Float> xRoller = new ArrayList<>();
    private List<String> yRoller = new ArrayList<>();
    private int yScale = 50;

    public GraphChatView(Context context) {
        this(context, null);
    }

    public GraphChatView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GraphChatView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.GraphChatView);
        tvColor = array.getColor(R.styleable.GraphChatView_graph_tvColor, tvColor);
        tvSize = array.getDimensionPixelSize(R.styleable.GraphChatView_graph_tvSize, dip2px(getContext(), tvSize));
        lineWidth = array.getDimensionPixelSize(R.styleable.GraphChatView_graph_lineWidth, dip2px(getContext(), lineWidth));
        lineColor = array.getColor(R.styleable.GraphChatView_graph_lineColor, lineColor);
        lineMarginLeft = array.getDimensionPixelSize(R.styleable.GraphChatView_graph_lineMarginLeft, dip2px(getContext(), lineMarginLeft));
        lineMarginBottom = array.getDimensionPixelSize(R.styleable.GraphChatView_graph_lineMarginBottom, dip2px(getContext(), lineMarginBottom));
        graphColor = array.getColor(R.styleable.GraphChatView_graph_graphColor, graphColor);
        graphWidth = array.getDimensionPixelSize(R.styleable.GraphChatView_graph_graphWidth, dip2px(getContext(), graphWidth));
        topPointWidth = array.getDimensionPixelSize(R.styleable.GraphChatView_graph_topPointWidth, dip2px(getContext(), topPointWidth));
        lineSmoothness = array.getFloat(R.styleable.GraphChatView_graph_lineSmoothness, lineSmoothness);
        array.recycle();
        init();
    }

    private void init() {
        tvPaint = new Paint();
        tvPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        tvPaint.setAntiAlias(true);
        tvPaint.setColor(tvColor);
        tvPaint.setTextSize(tvSize);

        linePaint = new Paint();
        linePaint.setStrokeWidth(lineWidth);
        linePaint.setColor(lineColor);

        graphPaint = new Paint();
        graphPaint.setColor(graphColor);
        graphPaint.setAntiAlias(true);
        graphPaint.setStrokeWidth(graphWidth);
        graphPaint.setStyle(Paint.Style.STROKE);

        bitmapPaint = new Paint();

        yRoller.add("0");
        yRoller.add("50");
        yRoller.add("100");
        yRoller.add("150");
        yRoller.add("200");
        yRoller.add("250");
    }

    /**
     * 设置Y轴刻度
     *
     * @param scale  单位刻度
     * @param strArr 刻度上展示的文字，若不设置则展示0,50,100,150,200,250
     */
    public void setYAxis(int scale, @NonNull String... strArr) {
        if (strArr.length > 0) {
            yScale = scale;
            yRoller = Arrays.asList(strArr);
            invalidate();
        }
    }

    public void setTempList(List<TempBean> tempList) {
        if (tempList.size() > 0) {
            this.tempList = tempList;
            invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //先画Y轴和横线
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

        if (tempList.size() <= 0) {
            return;
        }
        XRollerInfo infoList = getXRollerInfo();
        if (infoList != null) {
            xRoller.clear();
            xRoller.add(infoList.firstStr);
            xRoller.add(infoList.secondStr);
            xRoller.add(infoList.threeStr);
        }


        //画曲线
        int originX = (int) (yTvWidth + lineMarginLeft);
        int originY = (int) (getHeight() - ((3 * yTvHeight) >> 1) - lineMarginBottom - lineWidth);
        canvas.translate(originX, originY);
        canvas.save();

        for (int i = 0; i < xRoller.size(); i++) {
            float x = ((getWidth() - originX) * xRoller.get(i)) / infoList.getMaxMillis();
            //画X轴文字
            String text = String.valueOf(xRoller.get(i));
            Rect textBounds = new Rect();
            tvPaint.getTextBounds(text, 0, text.length(), textBounds);
            int baseLine = measureBaseLine(tvPaint, text, (int) (lineMarginBottom));
            canvas.drawText(text, x, baseLine, tvPaint);
        }

        //换算成对应的xy轴坐标
        //最开始的一个点的时间
        long timestampStart = tempList.get(0).getTimestamp();
        //最后一个点的时间
        long timeStampEnd = tempList.get(tempList.size() - 1).getTimestamp();
        //点的时间差，单位=秒
        long timeDifference = timeStampEnd - timestampStart;
        //x轴代表的总时长，单位=秒
        float xTotleTime = infoList.getMaxMillis() * 60;
        float scale = timeDifference / xTotleTime;
        mPointList.clear();
        int bitmapX = 0;
        int bitmapY = 0;
        for (int i = 0; i < tempList.size(); i++) {
            int temp = tempList.get(i).getTemp();
            long timestamp = tempList.get(i).getTimestamp();
            int y = temp * (originY + yTvHeight / 2) / (yScale * yRoller.size());
            int x = (int) ((timestamp - timestampStart) * (getWidth() - originX) * scale / (timeStampEnd - timestampStart));
            Point point = new Point(x, -y);
            mPointList.add(point);
            if (i == tempList.size() - 1) {
                bitmapX = x;
                bitmapY = -y;
            }
        }
        measurePath();
        Path dst = new Path();
        dst.rLineTo(0, 0);
        float distance = mPathMeasure.getLength() * drawScale;
        if (mPathMeasure.getSegment(0, distance, dst, true)) {
            //绘制曲线
            canvas.drawPath(dst, graphPaint);
        }

        //画终点
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.graph_chat_point);
        Bitmap newBitmap = scaleBitmap(bitmap, (int) topPointWidth);
        canvas.drawBitmap(newBitmap, bitmapX - topPointWidth / 2, bitmapY - topPointWidth / 2, bitmapPaint);
        canvas.restore();
    }

    /**
     * 计算贝塞尔曲线的Path和PathMeasure
     */
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
    private int measureBaseLine(Paint textPaint, String text, int topY) {
        Rect textBounds = new Rect();
        textPaint.getTextBounds(text, 0, text.length(), textBounds);
        Paint.FontMetricsInt fontMetricsInt = textPaint.getFontMetricsInt();
        int dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
        return topY + (textBounds.height() / 2) + dy;
    }

    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }

    private int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * bitmap 尺寸修改
     *
     * @param bm
     * @param toWidth
     * @return
     */
    private Bitmap scaleBitmap(Bitmap bm, int toWidth) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        // 计算缩放比例
        float scaleWidth = ((float) toWidth) / width;
        float scaleHeight = ((float) toWidth) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        return Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
    }

    /**
     * 根据传入的时间信息获取X轴信息
     *
     * @return
     */
    private XRollerInfo getXRollerInfo() {
        if (tempList.size() <= 0) {
            return null;
        }
        //最开始的一个点的时间
        long timestampStart = tempList.get(0).getTimestamp();
        //最后一个点的时间
        long timeStampEnd = tempList.get(tempList.size() - 1).getTimestamp();
        long timeDifference = timeStampEnd - timestampStart;
        //得到时间差也就是时长
        int millis = (int) (timeDifference / 60);
        if (millis < 2) {
            return new XRollerInfo(2, 0, 0.5f, 1, "分");
        } else if (millis < 4) {
            return new XRollerInfo(4, 0, 1, 2, "分");
        } else if (millis < 6) {
            return new XRollerInfo(6, 0, 2, 4, "分");
        } else if (millis < 8) {
            return new XRollerInfo(8, 0, 3, 6, "分");
        } else if (millis < 10) {
            return new XRollerInfo(10, 0, 4, 8, "分");
        } else if (millis < 12) {
            return new XRollerInfo(12, 0, 5, 10, "分");
        } else if (millis < 20) {
            return new XRollerInfo(20, 0, 6, 12, "分");
        } else if (millis < 30) {
            return new XRollerInfo(30, 0, 10, 20, "分");
        } else if (millis < 40) {
            return new XRollerInfo(40, 0, 15, 30, "分");
        } else if (millis < 50) {
            return new XRollerInfo(50, 0, 20, 40, "分");
        } else if (millis < 60) {
            return new XRollerInfo(60, 0, 25, 50, "分");
        } else if (millis < 70) {
            return new XRollerInfo(70, 0, 30, 60, "分");
        } else if (millis < 80) {
            return new XRollerInfo(85, 0, 35, 70, "分");
        } else if (millis < 100) {
            return new XRollerInfo(100, 0, 40, 80, "分");
        } else if (millis < 120) {
            return new XRollerInfo(120, 0, 50, 100, "分");
        } else if (millis < 4 * 60) {
            return new XRollerInfo(4 * 60, 0, 1, 2, "小时");
        } else if (millis < 8 * 60) {
            return new XRollerInfo(8 * 60, 0, 2, 4, "小时");
        } else if (millis < 12 * 60) {
            return new XRollerInfo(12 * 60, 0, 4, 8, "小时");
        } else if (millis < 16 * 60) {
            return new XRollerInfo(16 * 60, 0, 6, 12, "小时");
        } else if (millis < 20 * 60) {
            return new XRollerInfo(20 * 60, 0, 8, 16, "小时");
        } else if (millis < 24 * 60) {
            return new XRollerInfo(24 * 60, 0, 10, 20, "小时");
        } else if (millis < 36 * 60) {
            return new XRollerInfo(36 * 60, 0, 12, 24, "小时");
        } else if (millis < 48 * 60) {
            return new XRollerInfo(48 * 60, 0, 18, 36, "小时");
        } else {
            return new XRollerInfo(48 * 60, 0, 18, 36, "小时");
        }
    }

    /**
     * X轴信息
     */
    public static class XRollerInfo {
        public XRollerInfo(float maxMillis, float firstStr, float secondStr, float threeStr, String typeStr) {
            this.firstStr = firstStr;
            this.secondStr = secondStr;
            this.threeStr = threeStr;
            this.typeStr = typeStr;
            this.maxMillis = maxMillis;
        }

        float maxMillis;
        float firstStr;
        float secondStr;
        float threeStr;
        String typeStr;

        public float getMaxMillis() {
            return maxMillis;
        }

        public float getFirstStr() {
            return firstStr;
        }

        public float getSecondStr() {
            return secondStr;
        }

        public float getThreeStr() {
            return threeStr;
        }

        public String getTypeStr() {
            return typeStr;
        }
    }
}
