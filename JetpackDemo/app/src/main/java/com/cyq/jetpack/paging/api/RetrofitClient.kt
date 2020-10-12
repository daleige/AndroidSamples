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
    private const val MOVIE_BASE_URL = "http://t.yushu.im/v2/"
    private const val USER_BASE_URL = "https://api.stackexchange.com/"
    private var retrofit: Retrofit
    private var retrofit2: Retrofit

    init {
        /**
         * 请求豆瓣的
         */
        retrofit = Retrofit.Builder()
            .baseUrl(MOVIE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()

        /**
         * 请求Stack Overflow用户列表
         */
        retrofit2 = Retrofit.Builder()
            .baseUrl(USER_BASE_URL)
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

    fun getUserInfoApi(): Api {
        return retrofit2.create(Api::class.java)
    }
}