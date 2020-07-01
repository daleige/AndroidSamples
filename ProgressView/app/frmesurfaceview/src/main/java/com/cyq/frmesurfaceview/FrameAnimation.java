package com.cyq.frmesurfaceview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * Time: 2020/7/2 12:21 AM
 * Author: ChenYangQi
 * Description:
 */
public class FrameAnimation extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private SurfaceHolder mSurfaceHolder;

    private boolean mIsThreadRunning = true; // 线程运行开关
    public static boolean mIsDestroy = false;// 是否已经销毁

    private int[] mBitmapResourceIds;// 用于播放动画的图片资源id数组
    private ArrayList<String> mBitmapResourcePaths;// 用于播放动画的图片资源path数组
    private int totalCount;//资源总数
    private Canvas mCanvas;
    private Bitmap mBitmap;// 显示的图片

    private int mCurrentIndext;// 当前动画播放的位置
    private int mGapTime = 150;// 每帧动画持续存在的时间
    private boolean mIsRepeat = false;

    private OnFrameFinishedListener mOnFrameFinishedListener;// 动画监听事件

    public FrameAnimation(Context context) {
        this(context, null);
        initView();
    }

    public FrameAnimation(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    public FrameAnimation(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        initView();

    }

    private void initView() {

        mSurfaceHolder = this.getHolder();
        mSurfaceHolder.addCallback(this);

        // 白色背景
        setZOrderOnTop(true);
        setZOrderMediaOverlay(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // 当surfaceView销毁时, 停止线程的运行. 避免surfaceView销毁了线程还在运行而报错.
//        mIsThreadRunning = false;
//        try {
//            Thread.sleep(mGapTime);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        mIsDestroy = true;
    }

    /**
     * 制图方法
     */
    private void drawView() {
        // 无资源文件退出
        if (mBitmapResourceIds == null && mBitmapResourcePaths == null) {
            Log.e("frameview", "the bitmapsrcIDs is null");

            mIsThreadRunning = false;

            return;
        }

        // 锁定画布
        if (mSurfaceHolder != null) {
            mCanvas = mSurfaceHolder.lockCanvas();
        }
        try {
            if (mSurfaceHolder != null && mCanvas != null) {

                mCanvas.drawColor(Color.WHITE);

                if (mBitmapResourceIds != null && mBitmapResourceIds.length > 0)
                    mBitmap = BitmapFactory.decodeResource(getResources(), mBitmapResourceIds[mCurrentIndext]);
                else if (mBitmapResourcePaths != null && mBitmapResourcePaths.size() > 0) {
                    mBitmap = BitmapFactory.decodeFile(mBitmapResourcePaths.get(mCurrentIndext));

                }

                Paint paint = new Paint();
                paint.setAntiAlias(true);
                paint.setStyle(Paint.Style.STROKE);
                Rect mSrcRect, mDestRect;
                mSrcRect = new Rect(0, 0, mBitmap.getWidth(), mBitmap.getHeight());
                mDestRect = new Rect(0, 0, getWidth(), getHeight());
                mCanvas.drawBitmap(mBitmap, mSrcRect, mDestRect, paint);

                // 播放到最后一张图片
                if (mCurrentIndext == totalCount - 1) {
                    //TODO 设置重复播放
                    //播放到最后一张，当前index置零
                    mCurrentIndext = 0;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            mCurrentIndext++;

            if (mCurrentIndext >= totalCount) {
                mCurrentIndext = 0;
            }
            if (mCanvas != null) {
                // 将画布解锁并显示在屏幕上
                if (mSurfaceHolder != null) {
                    mSurfaceHolder.unlockCanvasAndPost(mCanvas);
                }
            }

            if (mBitmap != null) {
                // 收回图片
                mBitmap.recycle();
            }
        }
    }

    @Override
    public void run() {
        if (mOnFrameFinishedListener != null) {
            mOnFrameFinishedListener.onStart();
        }

        // 每隔150ms刷新屏幕
        while (mIsThreadRunning) {
            drawView();
            try {
                Thread.sleep(mGapTime);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (mOnFrameFinishedListener != null) {
            mOnFrameFinishedListener.onStop();
        }
    }

    /**
     * 开始动画
     */
    public void start() {
        if (!mIsDestroy) {
            mCurrentIndext = 0;
            mIsThreadRunning = true;
            new Thread(this).start();
        } else {
            // 如果SurfaceHolder已经销毁抛出该异常
            try {
                throw new Exception("IllegalArgumentException:Are you sure the SurfaceHolder is not destroyed");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 设置动画播放素材的id
     *
     * @param bitmapResourceIds 图片资源id
     */
    public void setBitmapResoursID(int[] bitmapResourceIds) {
        this.mBitmapResourceIds = bitmapResourceIds;
        totalCount = bitmapResourceIds.length;
    }

    /**
     * 设置动画播放素材的路径
     *
     * @param bitmapResourcePaths
     */
    public void setmBitmapResourcePath(ArrayList bitmapResourcePaths) {
        this.mBitmapResourcePaths = bitmapResourcePaths;
        totalCount = bitmapResourcePaths.size();
    }

    /**
     * 设置每帧时间
     */
    public void setGapTime(int gapTime) {
        this.mGapTime = gapTime;
    }

    /**
     * 结束动画
     */
    public void stop() {
        mIsThreadRunning = false;
    }

    /**
     * 继续动画
     */
    public void reStart() {
        mIsThreadRunning = false;
    }

    /**
     * 设置动画监听器
     */
    public void setOnFrameFinisedListener(OnFrameFinishedListener onFrameFinishedListener) {
        this.mOnFrameFinishedListener = onFrameFinishedListener;
    }

    /**
     * 动画监听器
     *
     * @author qike
     */
    public interface OnFrameFinishedListener {

        /**
         * 动画开始
         */
        void onStart();

        /**
         * 动画结束
         */
        void onStop();
    }

    /**
     * 当用户点击返回按钮时，停止线程，反转内存溢出
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 当按返回键时，将线程停止，避免surfaceView销毁了,而线程还在运行而报错
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            mIsThreadRunning = false;
        }

        return super.onKeyDown(keyCode, event);
    }

}
