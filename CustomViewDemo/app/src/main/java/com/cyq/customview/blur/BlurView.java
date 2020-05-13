package com.cyq.customview.blur;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author : ChenYangQi
 * date   : 2020/5/12 15:16
 * desc   : 高斯模糊View测试
 */
public class BlurView extends View {
    private Paint mPaint;
    private RadialGradient mRadialGradient;
    private int centerColor = Color.parseColor("#FFFFFFFF");
    private int middleColor = Color.parseColor("#CCFFFFFF");
    private int endColor = Color.parseColor("#4DFFFFFF");
    private int[] colors = {centerColor, middleColor, endColor};
    private float[] stops = {0.0F, 0.8F, 1.0f};
    private Paint mPaint2;
    private Paint mPaint3;
    private Paint mPaint4;
    private Paint mPaint5;

    public BlurView(Context context) {
        this(context, null);
    }

    public BlurView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BlurView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        mRadialGradient = new RadialGradient(500, 500, 50, colors, stops, Shader.TileMode.CLAMP);
        mPaint.setShader(mRadialGradient);

        mPaint2 = new Paint();
        mPaint2.setColor(Color.WHITE);
        mPaint2.setStyle(Paint.Style.FILL);
        mPaint2.setMaskFilter(new BlurMaskFilter(25, BlurMaskFilter.Blur.NORMAL));

        mPaint3 = new Paint();
        mPaint3.setColor(Color.WHITE);
        mPaint3.setStyle(Paint.Style.FILL);
        mPaint3.setShadowLayer(20, 10, 10, Color.WHITE);

        mPaint4 = new Paint();
        mPaint4.setStrokeWidth(40);
        mPaint4.setColor(Color.GREEN);
        mPaint4.setStyle(Paint.Style.STROKE);

        mPaint5 = new Paint();
        mPaint5.setStyle(Paint.Style.STROKE);
        mPaint5.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //RadialGradient实现羽化效果
        canvas.drawCircle(500, 500, 50, mPaint);
        //BlurMashFilter实现羽化效果
        canvas.drawCircle(500, 700, 50, mPaint2);
        //setShadowLayout实现阴影
        canvas.drawCircle(500, 900, 50, mPaint3);

        //测试边框
        canvas.drawRect(400, 1200, 800, 1500, mPaint4);
        canvas.drawRect(400, 1200, 800, 1500, mPaint5);
    }
}
