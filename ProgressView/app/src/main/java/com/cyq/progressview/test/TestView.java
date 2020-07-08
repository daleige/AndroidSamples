package com.cyq.progressview.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.cyq.progressview.R;

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

//        RectF rect = new RectF(0, 0, 25, 140);
//
//        Paint mBitmapPaint = new Paint();
//        //setLayerType(LAYER_TYPE_SOFTWARE, null);
//        Bitmap bitmapDST = BitmapFactory.decodeResource(getResources(), R.drawable.indicator);
//        Bitmap bitmapSRT = Bitmap.createBitmap(bitmapDST.getWidth(), bitmapDST.getHeight(), Bitmap.Config.ARGB_8888);
//        bitmapSRT.eraseColor(Color.parseColor("#FF0000"));
//        int layoutId = canvas.saveLayer(rect, null, Canvas.ALL_SAVE_FLAG);
//        canvas.drawBitmap(bitmapDST, null, rect, mBitmapPaint);
//        mBitmapPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY));
//        canvas.drawBitmap(bitmapSRT, null, rect, mBitmapPaint);
//        mBitmapPaint.setXfermode(null);
//        canvas.restoreToCount(layoutId);
    }

}
