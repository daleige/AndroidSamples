package com.cyq.http.bean

/**
 * @author : ChenYangQi
 * date   : 2021/1/10 18:34
 * desc   :
 */
data class TokenBean(
    val code: Int,
    val `data`: Data1,
    val description: String
)

data class Data1(
    val accessToken: String,
    val refreshToken: String
)