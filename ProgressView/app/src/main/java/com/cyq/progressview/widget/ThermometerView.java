package com.cyq.progressview.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.cyq.progressview.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author : ChenYangQi
 * date   : 2020/5/6 14:24
 * desc   : 温度进度控件
 */
public class ThermometerView extends View {
    private Context mContext;
    //控件宽高
    private int width, height;
    //粒子圆环的宽度
    private int mCircleWidth;
    //粒子总个数
    private int pointCount = 30;
    //粒子移动速度
    private double pointMoveSpeed = 0.5;
    //粒子列表
    private List<Point> mPointList = new ArrayList<>(pointCount);
    //粒子外层圆环原点坐标和半径长度
    private int centerX, centerY, radius;
    //粒子外层圆环的画笔
    private Paint mCirclePaint;
    //粒子画笔
    private Paint mPointPaint;
    //白色
    private int whiteColor = Color.parseColor("#FFFFFFFF");

    public ThermometerView(Context context) {
        this(context, null);
    }

    public ThermometerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ThermometerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mContext = getContext();
        width = Utils.dip2px(300, mContext);
        height = Utils.dip2px(300, mContext);
        mCircleWidth = Utils.dip2px(10, mContext);
        centerX = width / 2;
        centerY = width / 2;
        radius = width / 2 - mCircleWidth / 2;

        mCirclePaint = new Paint();
        mCirclePaint.setColor(whiteColor);
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(mCircleWidth);

        mPointPaint = new Paint();
        mPointPaint.setColor(whiteColor);
        mPointPaint.setAntiAlias(true);
        mPointPaint.setStyle(Paint.Style.FILL);

        mRandom = new Random();

        mPointList.clear();
        Point point = new Point();
        point.setAlpha(1);
        for (int i = 0; i < pointCount; i++) {
            //通过clone创建对象，避免重复创建
            Point clonePoint = point.clone();
            clonePoint.setRadius(10);
            clonePoint.setCenterX(mRandom.nextInt(width));
            clonePoint.setCenterY(height / 2);
            mPointList.add(clonePoint);
        }

        //画运动粒子
        ValueAnimator animator = ValueAnimator.ofFloat(1F, 1000F);
        animator.setDuration(10000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                for (int i = 0; i < pointCount; i++) {
                    pointY = (int) (((float) animation.getAnimatedValue() / 1000F) * height);
                    mPointList.get(i).setCenterY(pointY);
                }
                //更新坐标数据后，重绘粒子的坐标位置
                invalidate();
            }
        });
        animator.start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        //画外层圆环
        canvas.drawCircle(centerX, centerY, radius, mCirclePaint);
        //话动画粒子
        for (Point point : mPointList) {
            canvas.drawCircle(point.getCenterX(), point.getCenterY(), point.getRadius(), mPointPaint);
        }
    }

    private int pointX, pointY, pointRadius;

    /**
     * 粒子y轴随机变化的取值器
     */
    private Random mRandom;

    /**
     * 画运动的粒子
     *
     * @param canvas
     */
    private void drawPoints(final Canvas canvas) {

    }
}
