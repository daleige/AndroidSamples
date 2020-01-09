package com.cyq.customview.shadowLayout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.cyq.customview.R;

/**
 * @author : ChenYangQi
 * date   : 2020/1/9 9:50
 * desc   : ShadowLayout阴影效果测试类
 */
public class ShadowLayoutView extends View {
    private Paint mPaint;
    private Bitmap mBitmap;

    public ShadowLayoutView(Context context) {
        this(context, null);
    }

    public ShadowLayoutView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShadowLayoutView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(50);
        mPaint.setShadowLayer(10, 20, 20, Color.RED);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.shadow_test);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText("Paint设置阴影效果", 0, 100, mPaint);
        canvas.drawCircle(200, 200, 60, mPaint);
        canvas.drawBitmap(mBitmap, 500, 20, mPaint);
    }
}
