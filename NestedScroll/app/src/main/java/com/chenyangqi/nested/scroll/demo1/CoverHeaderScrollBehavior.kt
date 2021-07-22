package com.chenyangqi.nested.scroll.demo1

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import com.chenyangqi.nested.scroll.MyApplication
import com.chenyangqi.nested.scroll.R

/**
 * @describe
 * @author chenyangqi
 * @time 2021/7/22 15:02
 */
class CoverHeaderScrollBehavior<V : View>(context: Context, attributeSet: AttributeSet?) :
    CoordinatorLayout.Behavior<View>(context, attributeSet) {

    private val TAG = "CoverHeaderScrollBehavior"

    override fun onLayoutChild(
        parent: CoordinatorLayout,
        child: View,
        layoutDirection: Int
    ): Boolean {
        Log.d(TAG, "onLayoutChild----->")
        child.translationY = getHeaderViewHeight()
        return super.onLayoutChild(parent, child, layoutDirection)
    }

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL
    }

    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
        if (dy > 0) {
            //向上滑动
            val distanceY = child.translationY - dy
            if (distanceY > 0) {
                child.translationY = distanceY
                consumed[1] = dy
            }
        }
    }

    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int,
        consumed: IntArray
    ) {
        super.onNestedScroll(
            coordinatorLayout,
            child,
            target,
            dxConsumed,
            dyConsumed,
            dxUnconsumed,
            dyUnconsumed,
            type,
            consumed
        )
        Log.d(TAG, "dyUnconsumed=$dyUnconsumed    translationY=${child.translationY}")
        if (dyUnconsumed < 0) {
            val distanceY = child.translationY - dyUnconsumed
            if (distanceY > 0 && distanceY < getHeaderViewHeight()) {
                child.translationY = distanceY
            }
        }
    }

    private fun getHeaderViewHeight(): Float {
        return MyApplication.getAppContext().resources.getDimension(R.dimen.header_view_height)
    }
}