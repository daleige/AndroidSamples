package com.example.network.http

import com.example.network.http.gson.GsonBuilderUtil
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object HttpUtil {
    private val mRetrofit: Retrofit
    private val mOkHttpClient: OkHttpClient

    init {
        mOkHttpClient = initOkHttpClient()
        mRetrofit = initRetrofit()
    }

    private fun initOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .readTimeout(HttpConstant.TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(HttpConstant.TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(HttpConstant.TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    private fun initRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(HttpConstant.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilderUtil.gson))
            .client(mOkHttpClient)
            .build()
    }

    fun <T> createService(service: Class<T>): T {
        return mRetrofit.create(service)
    }
}