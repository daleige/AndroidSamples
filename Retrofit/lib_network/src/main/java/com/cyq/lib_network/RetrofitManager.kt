package com.cyq.lib_network

import com.google.gson.Gson
import com.google.gson.JsonObject
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.GsonConverterFactory
import retrofit2.Retrofit
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * @describe Retrofit管理类
 * @author chenyq113@midea.com
 * @time 2021/1/28 16:33
 */
object RetrofitManager {
    private lateinit var mRetrofit: Retrofit

    fun initRetrofit() {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(RspCheckInterceptor())
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()

        mRetrofit = Retrofit.Builder()
            .baseUrl(Appconfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            //添加Rxjava支持
            //.addCallAdapterFactory()
            .client(okHttpClient)
            .build()
    }

    fun <T> createReq(reqServer: Class<T>): T {
        return mRetrofit.create(reqServer)
    }


    /**
     * 静态内部类：请求结果校验检查器，判断请求是否成功，返回数据格式是否正确
     */
    class RspCheckInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val response = chain.proceed(chain.request())
            val jsonObject: JSONObject = JSONObject(response.body().toString())
            if (jsonObject.getInt("code") < 200 || jsonObject.getInt("code") > 300) {
                throw IOException(
                    jsonObject.getString("description")
                )
            }
            return response
        }
    }
}