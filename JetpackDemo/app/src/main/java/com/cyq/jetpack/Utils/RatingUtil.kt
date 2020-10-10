package com.cyq.jetpack.Utils

/**
 * @author : ChenYangQi
 * date   : 2020/10/10 14:32
 * desc   :
 */
object RatingUtil {
    @JvmStatic
    fun getRating(rating: Int): String {
        return when (rating) {
            1 -> "一星"
            2 -> "二星"
            3 -> "三星"
            4 -> "四星"
            5 -> "五星好评"
            else -> "暂无评分"
        }
    }
}