package com.cyq.jetpack.paging.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author : ChenYangQi
 * date   : 2020/10/11 22:45
 * desc   : 网络请求
 */
object RetrofitClient {
    private const val BASE_URL = "http://t.yushu.im/v2/"
    private var retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()
    }

    fun getInstance(): RetrofitClient {
        return this
    }

    fun getApi(): Api {
        return retrofit.create(Api::class.java)
    }
}