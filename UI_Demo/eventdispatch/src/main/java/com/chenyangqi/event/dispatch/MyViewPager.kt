package com.chenyangqi.event.dispatch

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager
import kotlin.math.abs

/**
 * 外部拦截
 */
class MyViewPager : ViewPager {
    private var lastX = 0f
    private var lastY = 0f

    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        val actionMasked = ev.actionMasked
        if (actionMasked == MotionEvent.ACTION_DOWN || actionMasked == MotionEvent.ACTION_UP) {
            lastX = ev.x
            lastY = ev.y
            //不拦截down和up
            return super.onInterceptTouchEvent(ev)
        }
        return if (abs(lastX - ev.x) > abs(lastY - ev.y)) {
            true
        } else {
            super.onInterceptTouchEvent(ev)
        }
    }
}