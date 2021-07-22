package com.chenyangqi.nested.scroll.demo1

import android.content.Context
import android.media.Image
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import com.chenyangqi.nested.scroll.MyApplication
import com.chenyangqi.nested.scroll.R

/**
 * @describe
 * @author chenyangqi
 * @time 2021/7/22 16:07
 */
class HeaderViewBehavior<V : View>(context: Context, attributeSet: AttributeSet?) :
    CoordinatorLayout.Behavior<View>(context, attributeSet) {
    private val TAG = "HeaderViewBehavior"

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        val behavior: CoordinatorLayout.Behavior<*>? =
            (dependency.layoutParams as CoordinatorLayout.LayoutParams).behavior
        return behavior != null && behavior is CoverHeaderScrollBehavior<*>
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        Log.d(
            "test",
            "onDependentViewChanged--->dependency.translationY=${dependency.translationY}"
        )
        if (dependency.translationY > 0 && dependency.translationY < getHeaderViewHeight()) {
            child.translationY = -(getHeaderViewHeight() - dependency.translationY) * 0.5F
            val viewGroup: ViewGroup = child as ViewGroup
            val bgImageView = viewGroup.getChildAt(0)
            val scale: Float =
                ((getHeaderViewHeight() - dependency.translationY) / getHeaderViewHeight() * 0.2F + 1F)
            Log.d(TAG, "1111--->$scale")
            bgImageView.scaleX = scale
            bgImageView.scaleY = scale
        }
        return super.onDependentViewChanged(parent, child, dependency);
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
        Log.d(TAG, "onNestedPreScroll--->dy=$dy")
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
        Log.d(TAG, "onNestedScroll--->dyUnconsumed=$dyUnconsumed")

    }

    private fun getHeaderViewHeight(): Float {
        return MyApplication.getAppContext().resources.getDimension(R.dimen.header_view_height)
    }
}