package com.chenyangqi.nested.scroll.nesting

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.NestedScrollingParent3
import androidx.core.view.ViewCompat

/**
 * @describe
 * @author chenyangqi
 * @time 2021/7/19 18:06
 */
class MyNestedScrollPreView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), NestedScrollingParent3 {

    private var mHeaderView: View? = null
    private var mHeaderHeight = 0

    override fun onStartNestedScroll(child: View, target: View, axes: Int, type: Int): Boolean {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL
    }

    override fun onNestedScrollAccepted(child: View, target: View, axes: Int, type: Int) {

    }

    override fun onStopNestedScroll(target: View, type: Int) {

    }

    override fun onNestedScroll(
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int,
        consumed: IntArray
    ) {

    }

    override fun onNestedScroll(
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int
    ) {

    }

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        val headerScrollUp = dy > 0 && scrollY < mHeaderHeight
        val headerScrollDown = dy < 0 && scrollY > 0 && !target.canScrollVertically(-1)
        if (headerScrollUp || headerScrollDown) {
            val dy1:Int = (dy * 0.5).toInt()
            scrollBy(0, dy1)
            consumed[1] = dy1
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        if (childCount > 0) {
            //第一个子控件是HeaderView
            mHeaderView = getChildAt(0)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mHeaderHeight = mHeaderView?.measuredHeight ?: 0
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val totalHeight =
            MeasureSpec.getSize(heightMeasureSpec + (mHeaderView?.measuredHeight ?: 0))
        val model = MeasureSpec.getMode(heightMeasureSpec)
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(totalHeight, model))
    }
}