package com.chenyangqi.gradle.test

import androidx.annotation.Keep

/**
 * @author : ChenYangQi
 * date   : 2021/8/2 23:35
 * desc   :
 */
class TestUtils {

    private fun test1() {
        println("----- test1")
    }

    @Keep
    fun test2() {
        println("----- test2")
    }

}