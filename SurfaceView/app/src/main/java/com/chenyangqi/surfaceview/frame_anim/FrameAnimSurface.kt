package com.chenyangqi.surfaceview.frame_anim

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView

/**
 * @author ChenYangQi
 * @describe
 * @time 2022/03/31 15:51
 */
class FrameAnimSurface @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : SurfaceView(context, attrs, defStyleAttr), SurfaceHolder.Callback {

    private var mSurfaceHolder: SurfaceHolder = holder
    private lateinit var mCanvas: Canvas
    private var mIsDrawing = false

    //当前播放图片的下标
    private var mCurrentIndex = 0

    //一张图片执行的时长
    private var mDuration = 60

    //复用的bitmap
    private var mBitmap: Bitmap? = null

    private var mResources = Util.getSourceId()

    private var mBitmapRect = Rect()

    private var mViewRect: Rect = Rect()

    private var mPaint: Paint = Paint()

    private var mOptions: BitmapFactory.Options = BitmapFactory.Options()

    init {
        //bitmap复用需设置可变类型
        mOptions.inMutable = true
        mOptions.inPreferredConfig = Bitmap.Config.RGB_565

        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, mResources[0], options)
        mBitmapRect.set(0, 0, options.outWidth, options.outHeight)

        mSurfaceHolder.addCallback(this)
        isFocusable = true
        isFocusableInTouchMode = true
        keepScreenOn = true
    }

    override fun surfaceCreated(p0: SurfaceHolder) {
        mViewRect.set(0, 0, width, height)
        mIsDrawing = true
        Thread {
            while (mIsDrawing) {
                for (resourceId in mResources) {
                    val beginTime = System.currentTimeMillis()
                    createBitmap(resourceId)
                    draw()
                    val userTime = System.currentTimeMillis() - beginTime
                    if (userTime < mDuration) {
                        Thread.sleep(mDuration - userTime)
                    }
                }
            }
        }.start()
    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
        mIsDrawing = false
    }

    private fun draw() {
        try {
            mCanvas = mSurfaceHolder.lockCanvas()
            mBitmap?.let { bmp ->
                mCanvas.drawBitmap(bmp, mBitmapRect, mViewRect, mPaint)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            mSurfaceHolder.unlockCanvasAndPost(mCanvas)
        }
    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {}

    private fun createBitmap(resourceId: Int) {
        mBitmap = BitmapFactory.decodeResource(resources, resourceId, mOptions)
        //复用bitmap
        mOptions.inBitmap = mBitmap
    }
}
