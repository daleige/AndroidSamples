package com.example.constraintlayout.demo

import android.content.Context
import android.util.AttributeSet
import android.view.ViewAnimationUtils
import androidx.constraintlayout.widget.ConstraintHelper
import androidx.constraintlayout.widget.ConstraintLayout

class MyConstraintHelper(context: Context?, attrs: AttributeSet?) :
    ConstraintHelper(context, attrs) {

    override fun updatePostLayout(container: ConstraintLayout?) {
        super.updatePostLayout(container)

        referencedIds.forEach {
            val view = container?.getViewById(it)
            ViewAnimationUtils.createCircularReveal(view, 0, 0, 0F, 90F)
                .setDuration(2000)
                .start()
        }
    }
}