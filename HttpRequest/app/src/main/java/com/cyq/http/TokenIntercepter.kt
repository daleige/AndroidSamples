package com.cyq.http

import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author : ChenYangQi
 * date   : 2021/1/10 23:50
 * desc   : 判断token过期并自动刷新的拦截器拦截器
 */
internal class TokenInterceptor : Interceptor {
    private val lock = Any()

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        var response = chain.proceed(request)
        //判断Token是否过期
        if (response.code() == 401) {


        }

        return response
    }


}