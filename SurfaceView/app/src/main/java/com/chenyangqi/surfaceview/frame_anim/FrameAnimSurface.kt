package com.chenyangqi.surfaceview.frame_anim

import android.content.Context
import android.graphics.Canvas
import android.os.Handler
import android.os.HandlerThread
import android.os.Message
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import java.lang.Exception
import java.util.*

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
    private var mHandler: Handler? = null


    init {
        val hThread = HandlerThread(this.javaClass.simpleName + "_" + UUID.randomUUID())
        hThread.start()
        mHandler = Handler(hThread.looper, object : Handler.Callback {
            override fun handleMessage(msg: Message): Boolean {
                while (mIsDrawing) {
                    draw()
                }
                return true
            }
        })

        mSurfaceHolder.addCallback(this)
        isFocusable = true
        isFocusableInTouchMode = true
        keepScreenOn = true
    }

    override fun surfaceCreated(p0: SurfaceHolder) {
        mIsDrawing = true
        mHandler?.sendEmptyMessage(1)
    }


    override fun surfaceDestroyed(p0: SurfaceHolder) {
        mIsDrawing = false
    }

    private fun draw() {
        try {
            Thread.sleep(1000)
            mCanvas = mSurfaceHolder.lockCanvas()

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            mSurfaceHolder.unlockCanvasAndPost(mCanvas)
        }
    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {}
}
