package com.chenyangqi.nested.scroll.nesting

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.NestedScrollingParent2
import androidx.core.view.NestedScrollingParentHelper
import androidx.core.view.ViewCompat

/**
 * @describe
 * @author chenyangqi
 * @time 2021/7/20 18:27
 */
class NestedLinearLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), NestedScrollingParent2 {
    private lateinit var mHeaderView: View
    private val mNestedScrollingParentHelper: NestedScrollingParentHelper =
        NestedScrollingParentHelper(this)

    //是否启用多指触控
    private var mNeedDragOver = true
    private var mNeedHackDispatchTouch = true
    private var mStickHeaderHeight = 50 * 3
    private var mTouchDownOnHeader = false
    private val mMaxHeaderHeight: Int =
        (TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            400f,
            resources.displayMetrics
        ) + 0.5).toInt()

    init {
        val gestureDetector =
            GestureDetector(getContext(), object : GestureDetector.SimpleOnGestureListener() {
                override fun onDown(e: MotionEvent?): Boolean {
                    return mNeedDragOver
                }

                override fun onScroll(
                    e1: MotionEvent?,
                    e2: MotionEvent?,
                    distanceX: Float,
                    distanceY: Float
                ): Boolean {
                    if (mTouchDownOnHeader) {
                        mNeedHackDispatchTouch = true
                        dispatchTouchEvent(e1)
                        dispatchTouchEvent(e2)
                    }
                    return mTouchDownOnHeader
                }
            })
        setOnTouchListener(fun(_: View, event: MotionEvent): Boolean {
            return gestureDetector.onTouchEvent(event)
        })
    }

    fun setHeaderBackground(image: View) {
        mHeaderView = image
        clipChildren = false
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return super.dispatchTouchEvent(ev)
    }

    override fun onStartNestedScroll(child: View, target: View, axes: Int, type: Int): Boolean {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL
    }

    override fun onNestedScrollAccepted(child: View, target: View, axes: Int, type: Int) {
        mNestedScrollingParentHelper.onNestedScrollAccepted(child, target, axes, type)
    }

    override fun onStopNestedScroll(target: View, type: Int) {
        mNestedScrollingParentHelper.onStopNestedScroll(target, type)
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
        if (dy > 0) {
            //向上滑动
            if (top > -(mMaxHeaderHeight - mStickHeaderHeight)) {
                consumed[1] = dy
                if (mHeaderView.translationY > 0) {
                    doOverScroll(mHeaderView.translationY - dy)
                }else{
                    scrollByOffsetTop(dy)
                }
            }
        } else {
            //向下滑动

        }
    }

    private fun doOverScroll(y: Float) {
        var targetTransY = y
        if (targetTransY < 0) {
            targetTransY = 0f
        }
        for (i in 0 until childCount) {
            getChildAt(i).translationY = targetTransY
        }
        val originHeight: Int = mHeaderView.measuredHeight
        val scale = (originHeight + targetTransY) * 1f / originHeight
        mHeaderView.scaleX = scale
        mHeaderView.scaleY = scale
    }

    private var mOldTop = 0
    private fun scrollByOffsetTop(dy: Int) {
        var dy = dy
        val oldTop = top
        val maxNeedHideHeight: Int = mMaxHeaderHeight
        var newTop = oldTop - dy
        if (newTop > 0) {
            dy = oldTop
        }
        if (newTop < -maxNeedHideHeight) {
            dy = maxNeedHideHeight + oldTop
        }
        newTop = oldTop - dy
        mOldTop = newTop
        offsetTopAndBottom(-dy)
    }

}