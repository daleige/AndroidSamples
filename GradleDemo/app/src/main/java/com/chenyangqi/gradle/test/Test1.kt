package com.chenyangqi.gradle.test

import androidx.annotation.Keep
import com.chenyangqi.gradle.test.bean.UserInfoBean

/**
 * @author : ChenYangQi
 * date   : 2021/8/2 23:37
 * desc   :
 */
@Keep
class Test1 {
    private val name = "张三"

    fun getName(): String {
        val userInfoBean = UserInfoBean(14, 1345345, "zhang  san")
        return name
    }
}