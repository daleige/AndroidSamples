package com.cyq.animdemo.pathmeasure.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator

/**
 *    @author : ChenYangQi
 *    date   : 2020/5/8 18:20
 *    desc   : PathMeasure练习的控件
 */
class PathMeasureView : View {
    private var mWidth: Float? = 0F
    private var mHeight: Float? = 0F
    private var mPath: Path = Path()
    private var mPathMeasure: PathMeasure? = null
    private var mLinePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mPointPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mCurrentPosition = FloatArray(2)
    private var mTanArr = FloatArray(2)
    private lateinit var mAnimator: ValueAnimator
    private var mValue: Float = 0.0f

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
            context,
            attrs,
            defStyleAttr
    )

    init {
        mLinePaint.strokeWidth = 2F
        mLinePaint.color = Color.RED
        mLinePaint.isAntiAlias = true
        mLinePaint.style = Paint.Style.STROKE

        mPointPaint.strokeWidth = 10F
        mPointPaint.color = Color.GREEN
        mPointPaint.isAntiAlias = true
        mPointPaint.style = Paint.Style.STROKE

        mWidth = width.toFloat()
        mHeight = height.toFloat()

        startAnim()
    }

    private fun startAnim() {
        //绘制三阶布尔塞尔曲线
        mPath.reset()
        mPath.moveTo(10F, 600F)
        mPath.cubicTo(220F, 750F, 580F, 450F, 700F, 600F)

        mPathMeasure = PathMeasure(mPath, false)
        mAnimator = ValueAnimator.ofFloat(0F, mPathMeasure!!.length)
        mAnimator.duration = 2000
        mAnimator.repeatMode = ValueAnimator.REVERSE
        mAnimator.repeatCount = ValueAnimator.INFINITE
        mAnimator.interpolator = AccelerateDecelerateInterpolator()
        mAnimator.addUpdateListener { animation ->
            mValue = animation?.animatedValue as Float
            mPathMeasure!!.getPosTan(mValue, mCurrentPosition, mTanArr)
            invalidate()
        }
        mAnimator.start()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawPath(mPath, mLinePaint)
        canvas?.drawPoint(mCurrentPosition[0], mCurrentPosition[1], mPointPaint)
    }
}