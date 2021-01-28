package com.cyq.retrofit

/**
 * @describe xxx
 * @author chenyq113@midea.com
 * @time 2021/1/28 17:45
 */
data class Person(
    val code: Int,
    val `data`: Data,
    val description: String
)

data class Data(
    val age: Int,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String
)