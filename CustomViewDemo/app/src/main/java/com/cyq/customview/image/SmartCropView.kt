package com.cyq.customview.image

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatImageView
import com.cyq.customview.R
import kotlin.math.sqrt
import kotlin.properties.Delegates

/**
 * @describe
 * @author chenyq113@midea.com
 * @time 2021/4/20 14:52
 */
class SmartCropView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : AppCompatImageView(context, attrs, defStyleAttr), SmartCropInterface {
    private var mBorderLinePaint = Paint()
    private var mBorderPointPaint = Paint()
    private var mCenterX: Float = 0F
    private var mCenterY: Float = 0F
    private val mPath = Path()
    private val mPoint1 = Point()
    private val mPoint2 = Point()
    private val mPoint3 = Point()
    private val mPoint4 = Point()
    private var mPoints = arrayOf(mPoint1, mPoint2, mPoint3, mPoint4)

    private var mBitmap: Bitmap? = null
    private var mMatrix: Matrix? = null
    private var isCrop = false

    companion object {
        const val BORDER_LINE_COLOR = Color.BLUE
        const val BORDER_POINT_COLOR = Color.BLUE
    }

    init {
        mBorderLinePaint.color = BORDER_LINE_COLOR
        mBorderLinePaint.strokeWidth = 10F
        mBorderLinePaint.isAntiAlias = true
        mBorderLinePaint.style = Paint.Style.STROKE

        mBorderPointPaint.color = BORDER_POINT_COLOR
        mBorderPointPaint.strokeWidth = 30F
        mBorderPointPaint.isAntiAlias = true

        scaleType = ScaleType.CENTER_INSIDE

        mPoint1.x = -400
        mPoint1.y = -250
        mPoint2.x = 400
        mPoint2.y = -250
        mPoint3.x = 400
        mPoint3.y = 250
        mPoint4.x = -400
        mPoint4.y = 250

        mMatrix = Matrix()
        val options = BitmapFactory.Options()
        options.inTargetDensity = 480 / 3
        mBitmap = BitmapFactory.decodeResource(resources, R.drawable.id_card, options)
    }

    private var index by Delegates.notNull<Int>()

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                index = pointDistance(event.x - mCenterX, event.y - mCenterY)
                if (index == 0) {
                    return false
                }
            }
            MotionEvent.ACTION_MOVE -> {
                when (index) {
                    1 -> {
                        mPoint1.x = (event.x - mCenterX).toInt()
                        mPoint1.y = (event.y - mCenterY).toInt()
                        invalidate()
                    }
                    2 -> {
                        mPoint2.x = (event.x - mCenterX).toInt()
                        mPoint2.y = (event.y - mCenterY).toInt()
                        invalidate()
                    }
                    3 -> {
                        mPoint3.x = (event.x - mCenterX).toInt()
                        mPoint3.y = (event.y - mCenterY).toInt()
                        invalidate()
                    }
                    4 -> {
                        mPoint4.x = (event.x - mCenterX).toInt()
                        mPoint4.y = (event.y - mCenterY).toInt()
                        invalidate()
                    }
                }
            }
            MotionEvent.ACTION_UP -> {

            }
            MotionEvent.ACTION_CANCEL -> {

            }
        }
        return true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        mCenterX = width.toFloat() / 2F
        mCenterY = height.toFloat() / 2F
        Log.d("test", "$width-------------$height")
        canvas?.save()
        canvas?.translate(mCenterX, mCenterY)
        mPath.reset()
        mPath.moveTo(mPoint1.x.toFloat(), mPoint1.y.toFloat())
        mPath.lineTo(mPoint2.x.toFloat(), mPoint2.y.toFloat())
        mPath.lineTo(mPoint3.x.toFloat(), mPoint3.y.toFloat())
        mPath.lineTo(mPoint4.x.toFloat(), mPoint4.y.toFloat())
        mPath.close()

        canvas?.drawPoint(mPoint1.x.toFloat(), mPoint1.y.toFloat(), mBorderPointPaint)
        canvas?.drawPoint(mPoint2.x.toFloat(), mPoint2.y.toFloat(), mBorderPointPaint)
        canvas?.drawPoint(mPoint3.x.toFloat(), mPoint3.y.toFloat(), mBorderPointPaint)
        canvas?.drawPoint(mPoint4.x.toFloat(), mPoint4.y.toFloat(), mBorderPointPaint)

        canvas?.drawPath(mPath, mBorderLinePaint)
        canvas?.restore()
        if(isCrop){
            canvas?.drawBitmap(mBitmap!!, mMatrix!!, null)
           // canvas?.setMatrix(mMatrix)
        }
    }

    /**
     * 计算触摸点距离四个顶点的距离，取最小的一个距离，如果小于50px，则表示触摸到了该点
     * 返回0，表示距离四个点位置都大于50
     * 返回1、2、3、4分别代表mPoint1,mPoint2,mPoint3,mPoint4
     */
    private fun pointDistance(x: Float, y: Float): Int {
        Log.d("test1", "x=$x-------------y=$y")
        mPoints = arrayOf(mPoint1, mPoint2, mPoint3, mPoint4)
        var finalDistance = Double.MAX_VALUE
        var result = 0
        for ((index, point) in mPoints.withIndex()) {
            val distance = sqrt((point.x.toDouble() - x) * (point.x - x) + (point.y - y) * (point.y - y))
            if (distance < finalDistance) {
                finalDistance = distance
                result = index + 1
            }
        }
        if (finalDistance < 50) {
            return result
        }
        return 0
    }

    override fun getPoints(): Array<Point> {
        mPoints = arrayOf(mPoint1, mPoint2, mPoint3, mPoint4)
        return mPoints
    }

    override fun crop() {
        mPoints = arrayOf(mPoint1, mPoint2, mPoint3, mPoint4)
        val src = floatArrayOf(
                mPoint1.x.toFloat(),
                mPoint1.y.toFloat(),
                mPoint2.x.toFloat(),
                mPoint2.y.toFloat(),
                mPoint3.x.toFloat(),
                mPoint3.y.toFloat(),
                mPoint4.x.toFloat(),
                mPoint4.y.toFloat())
        val dst = floatArrayOf(0F, 0F, (260 * 3).toFloat(), 0F, (260 * 3).toFloat(), (380 * 3)
                .toFloat(), 0F, (380 * 3).toFloat())
        mMatrix!!.setPolyToPoly(src, 0, dst, 0, src.size shr 1)
        isCrop = true
        invalidate()
    }

    override fun clockwiseRotate() {

    }

    override fun anticlockwiseRotate() {

    }
}