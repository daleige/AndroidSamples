package com.chenyangqi.nested.scroll.dmo2

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.RecyclerView

/**
 * @describe
 * @author chenyangqi
 * @time 2021/7/23 10:44
 */
class ScrollTitleBehavior<V : View>(context: Context, attributeSet: AttributeSet?) :
    CoordinatorLayout.Behavior<V>(context, attributeSet) {

    private var distanceY: Float = 0F

    override fun layoutDependsOn(parent: CoordinatorLayout, child: V, dependency: View): Boolean {
        return dependency is RecyclerView
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: V,
        dependency: View
    ): Boolean {
        if (distanceY == 0F) {
            distanceY = dependency.y - child.measuredHeight
        }
        var currentY: Float = dependency.y - child.height
        currentY = if (currentY < 0F) 0F else currentY
        val translationY: Float = -child.height * (currentY / distanceY)
        child.translationY = translationY
        return true
    }
}