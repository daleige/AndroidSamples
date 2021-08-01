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
 * @time 2021/7/19 15:35
 */
class BelowHeaderBehavior<V : View>(context: Context?, attrs: AttributeSet?) :
    CoordinatorLayout.Behavior<V>(context, attrs) {

    /**
     * 是否接受NestingScroll
     */
    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout, child: V,
        directTargetChild: View, target: View, axes: Int, type: Int
    ): Boolean {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL
    }

    /**
     * 接受NestingScroll的拦截
     */
    override fun onNestedScrollAccepted(
        coordinatorLayout: CoordinatorLayout, child: V, directTargetChild: View, target: View,
        axes: Int, type: Int
    ) {
        super.onNestedScrollAccepted(
            coordinatorLayout,
            child,
            directTargetChild,
            target,
            axes,
            type
        )
    }

    /**
     * NestingScroll结束
     */
    override fun onStopNestedScroll(
        coordinatorLayout: CoordinatorLayout, child: V, target: View, type: Int
    ) {
        super.onStopNestedScroll(coordinatorLayout, child, target, type)
    }

    /**
     * 嵌套滚动ing
     */
    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout, child: V, target: View, dxConsumed: Int,
        dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, type: Int, consumed: IntArray
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



    }

    /**
     * 嵌套滚动之前，告知子View我消耗了多少
     */
    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout, child: V, target: View, dx: Int, dy: Int,
        consumed: IntArray, type: Int
    ) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
    }

    /**
     * fling时
     */
    override fun onNestedFling(
        coordinatorLayout: CoordinatorLayout, child: V, target: View, velocityX: Float,
        velocityY: Float, consumed: Boolean
    ): Boolean {
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed)
    }

    /**
     * fling之前，可以由父元素消耗这一次的fling事件
     */
    override fun onNestedPreFling(
        coordinatorLayout: CoordinatorLayout, child: V, target: View, velocityX: Float,
        velocityY: Float
    ): Boolean {
        return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY)
    }

    override fun layoutDependsOn(parent: CoordinatorLayout, child: V, dependency: View): Boolean {
        val behavior = (dependency.layoutParams as CoordinatorLayout.LayoutParams).behavior
        return behavior.let { behavior is HeaderBehavior }
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout, child: V, dependency: View
    ): Boolean {
        Log.d("test", "dependency.bottom=${dependency.bottom}  child.top=${child.top}")
        ViewCompat.offsetTopAndBottom(child, dependency.bottom - child.top)
        return false
    }
}