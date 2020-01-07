package com.cyq.customview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.AttributeSet;
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
 * date   : 2020/1/7 9:58
 * desc   :
 */
public class NeBigView extends View implements GestureDetector.OnGestureListener,
        View.OnTouchListener, GestureDetector.OnDoubleTapListener {

    private final Rect mRect;
    private final BitmapFactory.Options mOptions;
    private final GestureDetector mGestureDetector;
    private final Scroller mScroller;
    private ScaleGestureDetector mScaleGestureDetector = null;
    private int mImageWidth;
    private int mImageHeight;
    private BitmapRegionDecoder mDecoder;
    private int mViewWidth;
    private int mViewHeight;
    private float mScale;
    private Bitmap mBitmap;
    private float originalScale;

    public NeBigView(Context context) {
        this(context,null);
    }

    public NeBigView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public NeBigView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 第1步：设置bigView需要成员变量
        mRect = new Rect();
        // 内存复用
        mOptions = new BitmapFactory.Options();
        // 手势识别
        mGestureDetector = new GestureDetector(context,this);
        // 滚动类
        mScroller = new Scroller(context);
        // 缩放手势识别
        mScaleGestureDetector = new ScaleGestureDetector(context,new ScaleGesture());
        setOnTouchListener(this);
    }

    // 第2步：设置图片
    public void setImage(InputStream is){
        // 获取图片的信息, 不能将整张图片加载进内存
        mOptions.inJustDecodeBounds = true;

        BitmapFactory.decodeStream(is,null,mOptions);
        mImageWidth = mOptions.outWidth;
        mImageHeight = mOptions.outHeight;

        // 开启复用
        mOptions.inMutable = true;
        // 设置格式
        mOptions.inPreferredConfig = Bitmap.Config.RGB_565;

        mOptions.inJustDecodeBounds = false;

        // 创建一个区域解码器
        try {
            mDecoder = BitmapRegionDecoder.newInstance(is,false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        requestLayout();
    }

    // 第3步，测量
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mViewWidth = getMeasuredWidth();
        mViewHeight = getMeasuredHeight();

        // 确定加载图片的区域
//        mRect.left = 0;
//        mRect.top = 0;
//        mRect.right = mImageWidth;
        // 得到图片的宽度，就能根据view的宽度计算缩放因子
//        mScale = mViewWidth/(float)mImageWidth;
//        mRect.bottom = (int)(mViewHeight/mScale);


        // 加了缩放手势之后的逻辑
        mRect.left = 0;
        mRect.top = 0;
        mRect.right = Math.min(mImageWidth,mViewWidth);
        mRect.bottom = Math.min(mImageHeight,mViewHeight);

        // 再定义一个缩放因子
        originalScale = mViewWidth/(float)mImageWidth;
        mScale = originalScale;
    }

    // 第4步，画出具体的内容
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mDecoder == null){
            return;
        }
        // 复用内存 // 复用bitmap必须跟即将解码的bitmap尺寸一样
        mOptions.inBitmap = mBitmap;
        mBitmap = mDecoder.decodeRegion(mRect,mOptions);

        Matrix matrix = new Matrix();

        matrix.setScale(mViewWidth/(float)mRect.width(),mViewWidth/(float)mRect.width());

        canvas.drawBitmap(mBitmap,matrix,null);
    }

    // 5：处理点击事件
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // 直接将时间传递给手势事件处理
        mGestureDetector.onTouchEvent(event);

        mScaleGestureDetector.onTouchEvent(event);

        return true;

    }

    // 6： 手按下去，处理事件
    @Override
    public boolean onDown(MotionEvent e) {
        // 如果移动没有停止，就强行停止
        if(!mScroller.isFinished()){
            mScroller.forceFinished(true);
        }
        return true;
    }

    // 7: 处理滑动事件
    // e1 就是开始事件，手指按下去，获取坐标
    // e2 当前事件
    // xy xy轴
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        // 上下移动的时候，mRect需要改变显示区域
        mRect.offset((int)distanceX,(int)distanceY);
        // 移动时，处理到达顶部和底部的情况
        if(mRect.bottom > mImageHeight){
            mRect.bottom = mImageHeight;
            mRect.top = mImageHeight-(int)(mViewHeight/mScale);
        }
        if(mRect.top < 0){
            mRect.top = 0;
            mRect.bottom = (int)(mViewHeight/mScale);
        }
        if(mRect.right > mImageWidth){
            mRect.right = mImageWidth;
            mRect.left = mImageWidth-(int)(mViewWidth/mScale);
        }
        if(mRect.left < 0 ){
            mRect.left = 0;
            mRect.right = (int)(mViewWidth/mScale);
        }
        invalidate();
        return false;
    }

    // 处理惯性问题
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        mScroller.fling(mRect.left,mRect.top,(int)-velocityX,(int)-velocityY,0,mImageWidth-(int)(mViewWidth/mScale),
                0,mImageHeight-(int)(mViewHeight/mScale));
        return false;
    }

    // 处理结果
    @Override
    public void computeScroll() {
        if(mScroller.isFinished()){
            return;
        }
        if(mScroller.computeScrollOffset()){
            mRect.top = mScroller.getCurrY();
            mRect.bottom = mRect.top+(int)(mViewHeight/mScale);
            invalidate();
        }
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }


    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        // 双击事件
        if(mScale < originalScale*1.5){
            mScale = originalScale*3;
        }else{
            mScale = originalScale;
        }
        mRect.right = mRect.left+(int)(mViewWidth/mScale);
        mRect.bottom = mRect.top+(int)(mViewHeight/mScale);
        // 移动时，处理到达顶部和底部的情况
        if(mRect.bottom > mImageHeight){
            mRect.bottom = mImageHeight;
            mRect.top = mImageHeight-(int)(mViewHeight/mScale);
        }
        if(mRect.top < 0){
            mRect.top = 0;
            mRect.bottom = (int)(mViewHeight/mScale);
        }
        if(mRect.right > mImageWidth){
            mRect.right = mImageWidth;
            mRect.left = mImageWidth-(int)(mViewWidth/mScale);
        }
        if(mRect.left < 0 ){
            mRect.left = 0;
            mRect.right = (int)(mViewWidth/mScale);
        }
        invalidate();
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    // 处理缩放的回调事件
    class ScaleGesture extends ScaleGestureDetector.SimpleOnScaleGestureListener{
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float scale = mScale;

            scale += detector.getScaleFactor() -1;
            if(scale <= originalScale){
                scale = originalScale;
            }else if(scale > originalScale*5){
                scale = originalScale*5;
            }
            mRect.right = mRect.left + (int)(mViewWidth/scale);
            mRect.bottom = mRect.top + (int)(mViewHeight/scale);
            mScale = scale;
            invalidate();
            return true;
        }
    }

}
