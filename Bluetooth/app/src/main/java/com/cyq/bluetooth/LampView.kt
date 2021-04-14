package com.cyq.bluetooth

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import kotlin.math.max
import kotlin.math.min


/**
 * @describe 自定义View，智能台灯
 * @author chenyq113@midea.com
 * @time 2021/4/9 11:09
 */
open class LampView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    View(context, attrs, defStyleAttr), ILampInterface {
    private var mWidth = 0
    private var mHeight = 0
    private lateinit var mPorterDuffXfermode: PorterDuffXfermode
    private lateinit var mBitmapDST: Bitmap
    private lateinit var mBitmapSRT: Bitmap
    private var mLampColor = Color.BLACK
    private lateinit var mRectF: RectF
    private lateinit var mPaint: Paint

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = h
        mRectF = RectF(0F, 0F, mWidth.toFloat(), mHeight.toFloat())
        mPorterDuffXfermode = PorterDuffXfermode(PorterDuff.Mode.MULTIPLY)
        mBitmapDST = thumbImageWithInSampleSize(mWidth.toFloat(), mHeight.toFloat())
        Log.d("test", "宽：$mWidth --高：$mHeight")
        mBitmapSRT = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888)
        mBitmapSRT.eraseColor(context.resources.getColor(R.color.black))
        mPaint = Paint()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //canvas?.translate((mWidth / 2).toFloat(), (mHeight / 2).toFloat())
        val layoutId = canvas?.saveLayer(mRectF, mPaint)
        mBitmapSRT.eraseColor(
            when (mLampColor) {
                Color.DEFAULT -> context.resources.getColor(R.color.white)
                Color.GREEN -> context.resources.getColor(R.color.green)
                Color.YELLOW -> context.resources.getColor(R.color.yellow)
                Color.RED -> context.resources.getColor(R.color.red)
                Color.BLACK -> context.resources.getColor(R.color.black)
            }
        )
        canvas?.drawBitmap(mBitmapDST, null, mRectF, mPaint)
        mPaint.xfermode = mPorterDuffXfermode
        canvas?.drawBitmap(mBitmapSRT, null, mRectF, mPaint)
        mPaint.xfermode = null
        layoutId?.let { canvas.restoreToCount(it) }
    }

    open fun thumbImageWithInSampleSize(
        destWidth: Float,
        destHeight: Float
    ): Bitmap {
        val opt = BitmapFactory.Options()
        opt.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.lamp, opt)
        val scaleW =
            max(destWidth, opt.outWidth.toFloat()) / (min(destWidth, opt.outWidth.toFloat()) * 1.0F)
        val scaleH =
            max(destHeight, opt.outHeight.toFloat()) / (min(
                destHeight,
                opt.outHeight.toFloat()
            ) * 1.0F)
        opt.inSampleSize = max(scaleW, scaleH).toInt()
        opt.inJustDecodeBounds = false
        return BitmapFactory.decodeResource(resources, R.drawable.lamp, opt)
    }

    /**
     * 灯光颜色的模式
     */
    enum class Color {
        YELLOW, GREEN, RED, DEFAULT, BLACK
    }

    /**
     * 开灯
     */
    override fun open() {
        mLampColor = Color.DEFAULT
        invalidate()
    }

    /**
     * 关灯
     */
    override fun close() {
        mLampColor = Color.BLACK
        invalidate()
    }

    /**
     * 设置灯光颜色模式
     */
    override fun setLampColor(color: Color) {
        mLampColor = color
        invalidate()
    }
}