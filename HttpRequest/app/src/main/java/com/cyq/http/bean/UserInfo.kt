package com.cyq.http.bean

/**
 * @author : ChenYangQi
 * date   : 2021/1/10 23:28
 * desc   :
 */
data class UserInfo(
    val code: Int,
    val `data`: Data2,
    val description: String
)

data class Data2(
    val account: String,
    val lastLoginTime: String,
    val password: String
)