package com.cyq.progressview.test;

import android.content.Context;
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
 * date   : 2020/6/24 14:06
 * desc   :
 */
public class TestView extends View {
    public TestView(Context context) {
        this(context, null);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    int endColor = Color.parseColor("#FFFF8700");
    int transparentColor = Color.parseColor("#00000000");
    int[] colors = {transparentColor, transparentColor, endColor};
    float[] posts = {0F, 0.6F, 1F};

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        Paint mSweptPaint = new Paint();
        mSweptPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        RadialGradient mRadialGradient = new RadialGradient(
                100,
                100,
                100,
                colors,
                posts,
                Shader.TileMode.CLAMP);
        mSweptPaint.setShader(mRadialGradient);
        canvas.save();
        canvas.drawCircle(100, 100, 100, mSweptPaint);


        Paint mPaint2 = new Paint();
        mPaint2.setColor(Color.WHITE);
        mPaint2.setStyle(Paint.Style.FILL);
        mPaint2.setMaskFilter(new BlurMaskFilter(25, BlurMaskFilter.Blur.NORMAL));

        canvas.drawCircle(400, 100, 25, mPaint2);


    }
}
