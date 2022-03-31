package com.chenyangqi.surfaceview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.SurfaceTexture
import android.util.AttributeSet
import android.view.TextureView
import java.lang.Exception
import java.util.*

/**
 * @author ChenYangQi
 * @describe
 * @time 2022/03/31 11:35
 */
class MyTextureView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : TextureView(context, attrs, defStyleAttr), TextureView.SurfaceTextureListener {
    private var mIsRunning = false
    private lateinit var mThread: Thread
    private var mFps = 1F
    private var mCanvas: Canvas? = null
    private var mColors =
        intArrayOf(Color.RED, Color.BLACK, Color.BLUE, Color.DKGRAY, Color.GREEN, Color.YELLOW)

    init {
        surfaceTextureListener = this
    }

    override fun onSurfaceTextureAvailable(p0: SurfaceTexture, p1: Int, p2: Int) {
        mIsRunning = true
        mThread = Thread {
            while (true) {
                val startTime = System.currentTimeMillis()
                drawContent()
                val needTime = startTime - System.currentTimeMillis()
                val oneFrameTime = 1000 / mFps
                if (needTime < oneFrameTime) {
                    Thread.sleep((oneFrameTime - needTime).toLong())
                }
            }
        }
        mThread.start()
    }

    private fun drawContent() {
        try {
            mCanvas = lockCanvas()
            mCanvas?.drawColor(mColors[Random().nextInt(mColors.size)])
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            mCanvas?.let {
                unlockCanvasAndPost(it)
            }
        }
    }

    override fun onSurfaceTextureSizeChanged(p0: SurfaceTexture, p1: Int, p2: Int) {

    }

    override fun onSurfaceTextureDestroyed(p0: SurfaceTexture): Boolean {
        mIsRunning = false
        return mIsRunning
    }

    override fun onSurfaceTextureUpdated(p0: SurfaceTexture) {

    }
}