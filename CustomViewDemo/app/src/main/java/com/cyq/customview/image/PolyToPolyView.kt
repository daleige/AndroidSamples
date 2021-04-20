package com.cyq.customview.image

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Matrix
import android.util.AttributeSet
import android.view.View
import com.cyq.customview.R

/**
 * @author chenyq113@midea.com
 * @describe xxx
 * @time 2021/4/20 10:42
 */
class PolyToPolyView @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {
    private var mBitmap: Bitmap? = null
    private var mMatrix: Matrix? = null
    private fun init() {
        val options = BitmapFactory.Options()
        options.inTargetDensity = 480 / 3
        mBitmap = BitmapFactory.decodeResource(resources, R.drawable.id_card, options)
        mMatrix = Matrix()
        val src = floatArrayOf(143f, 10f, 484f, 8f, 649f, 467f, 0f, 477f)
        //
        val dst = floatArrayOf(0f, 0f, (260 * 3).toFloat(), 0f, (260 * 3).toFloat(), (380 * 3).toFloat(), 0f, (380 * 3).toFloat())
        mMatrix!!.setPolyToPoly(src, 0, dst, 0, src.size shr 1)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (dell) {
            canvas.drawBitmap(mBitmap!!, mMatrix!!, null)
        }
    }

    private var dell = false
    fun dellImage(b: Boolean) {
        dell = b
        invalidate()
    }

    init {
        init()
    }
}