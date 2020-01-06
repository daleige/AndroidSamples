package com.cyq.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author : ChenYangQi
 * date   : 2020/1/6 10:58
 * desc   :
 */
public class WheelView extends View {
    private Paint mPaint;
    private Paint xFermodePaint;
    private Rect mRect2;

    public WheelView(Context context) {
        this(context, null);
    }

    public WheelView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WheelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setTextSize(50);
        mPaint.setStrokeWidth(5);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);

        xFermodePaint = new Paint();
        xFermodePaint.setColor(Color.RED);
        mRect2 = new Rect(100, 0, 400, 100);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //离屏绘制
        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), mPaint, Canvas.ALL_SAVE_FLAG);
        //禁用硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        canvas.drawText("Hello World", 0, 50, mPaint);
        xFermodePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawRect(mRect2, xFermodePaint);
        xFermodePaint.setXfermode(null);
        canvas.restoreToCount(layerId);
    }
}
