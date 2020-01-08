package com.cyq.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Scroller;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author : ChenYangQi
 * date   : 2020/1/7 10:28
 * desc   :
 */
public class BigImageView extends View implements GestureDetector.OnGestureListener, View.OnTouchListener {
    private Rect mRect;
    private BitmapFactory.Options mOptions;
    private GestureDetector mGestureDetector;
    private Scroller mScroller;
    private ScaleGestureDetector mScaleGestureDetector = null;
    private int mImageWidth;
    private int mImageHeight;
    private int mViewWidth;
    private int mViewHeight;
    private Bitmap mBitmap;
    private BitmapRegionDecoder mDecoder;
    private Matrix matrix = new Matrix();

    public BigImageView(Context context) {
        this(context, null);
    }

    public BigImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BigImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mRect = new Rect();
        // 内存复用
        mOptions = new BitmapFactory.Options();
        // 手势识别
        mGestureDetector = new GestureDetector(context, this);
        // 滚动类
        mScroller = new Scroller(context);
        setOnTouchListener(this);
    }

    public void setImage(InputStream is) {
        //只加载指定区域的bitmap
        mOptions.inJustDecodeBounds = true;
        if (is == null) {
            return;
        }
        BitmapFactory.decodeStream(is, null, mOptions);
        mImageWidth = mOptions.outWidth;
        mImageHeight = mOptions.outHeight;
        //开启复用
        mOptions.inMutable = true;
        //设置格式
        mOptions.inPreferredConfig = Bitmap.Config.RGB_565;
        mOptions.inJustDecodeBounds = false;
        //创建一个区域解码器
        try {
            mDecoder = BitmapRegionDecoder.newInstance(is, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mViewWidth = getMeasuredWidth();
        mViewHeight = getMeasuredHeight();
        mRect.left = 0;
        mRect.top = 0;
        mRect.right = mImageWidth;
        mRect.bottom = mImageHeight;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mDecoder == null) {
            return;
        }
        //内存復用，復用的Bitmap尺寸必须和解码区域尺寸一致
        mOptions.inBitmap = mBitmap;
        mBitmap = mDecoder.decodeRegion(mRect, mOptions);
        matrix.setScale(mViewWidth / (float) mRect.width(), mViewWidth / (float) mRect.width());
        canvas.drawBitmap(mBitmap, matrix, null);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.i("test", "distanceY:" + distanceY + "    distanceX:" + distanceX);
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return true;
    }
}
