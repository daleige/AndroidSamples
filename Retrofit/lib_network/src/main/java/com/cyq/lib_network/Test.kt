package com.cyq.lib_network

import retrofit2.Retrofit

/**
 * @author chenyq113@midea.com
 * @describe xxx
 * @time 2021/1/28 17:02
 */
class Test {
    private val mRetrofit: Retrofit? = null
    fun <T> create(reqServer: Class<T>?): T {
        return mRetrofit!!.create(reqServer)
    }
}