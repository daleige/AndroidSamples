package com.chenyangqi.nested.scroll.demo3

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat


/**
 * @describe
 * @author chenyangqi
 * @time 2021/7/23 11:15
 */
class Demo3RecycleviewBehavior<V : View>(context: Context, attributeSet: AttributeSet?) :
    CoordinatorLayout.Behavior<V>(context, attributeSet) {

    override fun layoutDependsOn(parent: CoordinatorLayout, child: V, dependency: View): Boolean {
        return dependency is RelativeLayout
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: V,
        dependency: View
    ): Boolean {
        var translationY: Float = dependency.measuredHeight - dependency.translationY
        translationY = if (translationY < 0) 0F else translationY
        child.y = translationY
        return true
    }

}