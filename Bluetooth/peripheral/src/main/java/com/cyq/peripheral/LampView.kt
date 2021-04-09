package com.cyq.peripheral

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View

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
    private val mPorterDuffXfermode: PorterDuffXfermode
    private val mBitmapDST: Bitmap
    private val mBitmapSRT: Bitmap
    private var mLampColor = Color.WHITE
    private val mRectF: RectF
    private val mPaint: Paint

    init {
        mWidth = 200
        mHeight = 200
        mRectF = RectF(0F, 0F, mWidth.toFloat(), mHeight.toFloat())
        mPorterDuffXfermode = PorterDuffXfermode(PorterDuff.Mode.MULTIPLY)
        mBitmapDST = BitmapFactory.decodeResource(resources, R.drawable.lamp)
        Log.d("test", "宽：$mWidth --高：$mHeight")
        mBitmapSRT = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888)
        mBitmapSRT.eraseColor(context.resources.getColor(R.color.black))
        mPaint = Paint()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.translate((mWidth / 2).toFloat(), (mHeight / 2).toFloat())
        val layoutId = canvas?.saveLayer(mRectF, mPaint)
        mBitmapSRT.eraseColor(
            when (mLampColor) {
                Color.WHITE -> context.resources.getColor(R.color.white)
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

    /**
     * 灯光颜色的模式
     */
    enum class Color {
        YELLOW, GREEN, RED, WHITE, BLACK
    }

    /**
     * 开灯
     */
   override fun open() {
        mLampColor = Color.WHITE
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