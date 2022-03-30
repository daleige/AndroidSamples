package com.chenyangqi.surfaceview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import java.lang.Exception
import java.util.*

/**
 * @author ChenYangQi
 * @describe
 * @time 2022/03/30 17:36
 */
class MySurfaceView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : SurfaceView(context, attrs, defStyleAttr), SurfaceHolder.Callback, Runnable {

    private var mSurfaceHolder: SurfaceHolder = holder
    private lateinit var mCanvas: Canvas
    private var mIsDrawing = false
    private var mBgColor: Int = Color.RED
    private var mColors =
        intArrayOf(Color.RED, Color.BLACK, Color.BLUE, Color.DKGRAY, Color.GREEN, Color.YELLOW)

    init {
        mSurfaceHolder.addCallback(this)
        isFocusable = true
        isFocusableInTouchMode = true
        keepScreenOn = true
    }

    override fun surfaceCreated(p0: SurfaceHolder) {
        mIsDrawing = true
        Thread(this).start()
    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
        mIsDrawing = false
    }

    override fun run() {
        while (mIsDrawing) {
            drawContent()
            mBgColor = mColors[Random().nextInt(mColors.size)]
        }
    }

    private fun drawContent() {
        try {
            Thread.sleep(100)

            mCanvas = mSurfaceHolder.lockCanvas()
            mCanvas.drawColor(mBgColor)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            mSurfaceHolder.unlockCanvasAndPost(mCanvas)
        }
    }
}