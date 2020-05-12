package com.cyq.customview.blur;

import android.content.Context;
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
    private int middleColor=Color.parseColor("#CCFFFFFF");
    private int endColor = Color.parseColor("#4DFFFFFF");
    private int[] colors = {centerColor, middleColor, endColor};
    private float[] stops = {0.0F, 0.8F, 1.0f};

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
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.FILL);
        mRadialGradient = new RadialGradient(500, 500, 50, colors, stops, Shader.TileMode.CLAMP);
        mPaint.setShader(mRadialGradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(500, 500, 50, mPaint);
    }
}
