package com.chenyangqi.nested.scroll.coordinator

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat

/**
 * @describe
 * @author chenyangqi
 * @time 2021/7/19 11:21
 */
class HeaderBehavior<V : View>(context: Context?, attrs: AttributeSet?) :
    CoordinatorLayout.Behavior<V>(context, attrs) {
    private val TAG: String = "CustomBehavior"

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: V,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        //是否非垂直滚动
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL
    }

    override fun layoutDependsOn(parent: CoordinatorLayout, child: V, dependency: View): Boolean {
//        val behavior = (dependency.layoutParams as CoordinatorLayout.LayoutParams).behavior
//        if (behavior is BelowHeaderBehavior) {
//            return true
//        }
        return super.layoutDependsOn(parent, child, dependency)
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: V,
        dependency: View
    ): Boolean {
        val behavior = (dependency.layoutParams as CoordinatorLayout.LayoutParams).behavior
        if (behavior is BelowHeaderBehavior) {
            translationHeaderView(behavior, child)
            return true
        }
        return super.onDependentViewChanged(parent, child, dependency)
    }

    private fun translationHeaderView(behavior: CoordinatorLayout.Behavior<View>?, child: V) {
        Log.d(TAG, "child.top=${child.top}")
    }
}