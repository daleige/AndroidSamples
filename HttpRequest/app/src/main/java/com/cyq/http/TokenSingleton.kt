package com.cyq.http

/**
 * @author : ChenYangQi
 * date   : 2021/1/10 17:58
 * desc   : 单例 用于存储accessToken和refreshToken
 */
class TokenSingleton private constructor() {
    lateinit var accessToken: String
    lateinit  var refreshToken: String

    companion object {
        val instance: TokenSingleton by lazy { TokenSingleton() }
    }
}