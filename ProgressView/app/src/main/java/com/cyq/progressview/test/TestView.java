package com.cyq.progressview.test;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author : ChenYangQi
 * date   : 2020/6/24 14:06
 * desc   :
 */
public class TestView extends View  {
    public TestView(Context context) {
        this(context, null);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                invalidate();
            }
        });
    }

    int endColor = Color.parseColor("#FFFF8700");
    int transparentColor = Color.parseColor("#00000000");
    int[] colors = {transparentColor, transparentColor, endColor};
    float[] posts = {0F, 0.6F, 1F};

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//        Paint mSweptPaint = new Paint();
//        mSweptPaint.setStyle(Paint.Style.FILL_AND_STROKE);
//        RadialGradient mRadialGradient = new RadialGradient(
//                100,
//                100,
//                100,
//                colors,
//                posts,
//                Shader.TileMode.CLAMP);
//        mSweptPaint.setShader(mRadialGradient);
//        canvas.save();
//        canvas.drawCircle(100, 100, 100, mSweptPaint);
//
//
//        Paint mPaint2 = new Paint();
//        mPaint2.setColor(Color.WHITE);
//        mPaint2.setStyle(Paint.Style.FILL);
//        mPaint2.setMaskFilter(new BlurMaskFilter(25, BlurMaskFilter.Blur.NORMAL));
//
//        canvas.drawCircle(400, 100, 25, mPaint2);


        canvas.translate(500, 500);
        int radius = 200;
        Point q1 = new Point();
        q1.x = (int) (radius * Math.cos(Math.toRadians(120)));
        q1.y = (int) (radius * Math.sin(Math.toRadians(120)));
        q1.anger = 120;

        Point q2 = new Point();
        q2.x = (int) (radius * Math.cos(Math.toRadians(240)));
        q2.y = (int) (radius * Math.sin(Math.toRadians(240)));
        q2.anger = 120;

        Point q3 = new Point();
        q3.x = (int) (radius * Math.cos(Math.toRadians(360)));
        q3.y = (int) (radius * Math.sin(Math.toRadians(360)));
        q3.anger = 120;

        Paint mPointPaint = new Paint();
        mPointPaint.setColor(Color.RED);
        mPointPaint.setStrokeWidth(10);

        Paint circlrPaint = new Paint();
        circlrPaint.setColor(Color.WHITE);
        circlrPaint.setStyle(Paint.Style.STROKE);

        canvas.drawCircle(0, 0, radius, circlrPaint);

        int controllerRadius = 300;
        Point cFirst1 = new Point();
        Point cFirst2 = new Point();
        Point cSecond1 = new Point();
        Point cSecond2 = new Point();
        Point cThree1 = new Point();
        Point cThree2 = new Point();

        Random random = new Random();

        int distance=30;
        cFirst1.anger = 60 - random.nextInt(distance);
        cFirst1.x = (int) (controllerRadius * Math.cos(Math.toRadians(cFirst1.anger)));
        cFirst1.y = (int) (controllerRadius * Math.sin(Math.toRadians(cFirst1.anger)));
        cFirst2.anger = 60 + random.nextInt(distance);
        cFirst2.x = (int) (controllerRadius * Math.cos(Math.toRadians(cFirst2.anger)));
        cFirst2.y = (int) (controllerRadius * Math.sin(Math.toRadians(cFirst2.anger)));

        cSecond1.anger = 180 - random.nextInt(distance);
        cSecond1.x = (int) (controllerRadius * Math.cos(Math.toRadians(cSecond1.anger)));
        cSecond1.y = (int) (controllerRadius * Math.sin(Math.toRadians(cSecond1.anger)));
        cSecond2.anger = 180 + random.nextInt(distance);
        cSecond2.x = (int) (controllerRadius * Math.cos(Math.toRadians(cSecond2.anger)));
        cSecond2.y = (int) (controllerRadius * Math.sin(Math.toRadians(cSecond2.anger)));

        cThree1.anger = 300 - random.nextInt(distance);
        cThree1.x = (int) (controllerRadius * Math.cos(Math.toRadians(cThree1.anger)));
        cThree1.y = (int) (controllerRadius * Math.sin(Math.toRadians(cThree1.anger)));
        cThree2.anger = 300 + random.nextInt(distance);
        cThree2.x = (int) (controllerRadius * Math.cos(Math.toRadians(cThree2.anger)));
        cThree2.y = (int) (controllerRadius * Math.sin(Math.toRadians(cThree2.anger)));

        Paint pathPaint = new Paint();
        pathPaint.setStyle(Paint.Style.STROKE);
        pathPaint.setColor(Color.GREEN);

        List<Point> mPointList = new ArrayList<>();
        canvas.drawPoint(q1.getX(), q1.getY(), mPointPaint);
        canvas.drawPoint(q2.getX(), q2.getY(), mPointPaint);
        canvas.drawPoint(q3.getX(), q3.getY(), mPointPaint);
        canvas.drawPoint(cFirst1.getX(), cFirst1.getY(), mPointPaint);
        canvas.drawPoint(cFirst2.getX(), cFirst2.getY(), mPointPaint);
        canvas.drawPoint(cSecond1.getX(), cSecond1.getY(), mPointPaint);
        canvas.drawPoint(cSecond2.getX(), cSecond2.getY(), mPointPaint);
        canvas.drawPoint(cThree1.getX(), cThree1.getY(), mPointPaint);
        canvas.drawPoint(cThree2.getX(), cThree2.getY(), mPointPaint);

        List<Point> mPathList = new ArrayList<>();
        Path path1 = new Path();
        path1.moveTo(q3.x, q3.y);
        path1.cubicTo(cFirst1.x, cFirst1.y, cFirst2.x, cFirst2.y, q1.x, q1.y);
        path1.cubicTo(cSecond1.x, cSecond1.y, cSecond2.x, cSecond2.y, q2.x, q2.y);
        path1.cubicTo(cThree1.x, cThree1.y, cThree2.x, cThree2.y, q3.x, q3.y);
        canvas.drawPath(path1, pathPaint);
    }

    class Point {
        public int x;
        public int y;
        public int anger;

        public Point() {
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getAnger() {
            return anger;
        }

        public void setAnger(int anger) {
            this.anger = anger;
        }

        public Point(int x, int y, int anger) {
            this.x = x;
            this.y = y;
            this.anger = anger;
        }
    }
}
