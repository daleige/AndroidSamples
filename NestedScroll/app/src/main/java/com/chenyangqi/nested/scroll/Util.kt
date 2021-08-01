package com.chenyangqi.nested.scroll

import android.content.Context
import android.util.TypedValue




/**
 * @describe
 * @author chenyangqi
 * @time 2021/7/22 15:06
 */
class Util {

    companion object{
        // 方法2
        fun dp2px(ctx: Context, dp: Float): Int {
            return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                ctx.resources.displayMetrics
            )
                .toInt()
        }
    }
}