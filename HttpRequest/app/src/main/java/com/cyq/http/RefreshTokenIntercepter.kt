package com.cyq.http

import android.text.TextUtils
import android.util.Log
import com.cyq.http.bean.TokenBean
import com.google.gson.Gson
import okhttp3.*

/**
 * @author : ChenYangQi
 * date   : 2021/1/10 23:50
 * desc   : 判断token过期并自动刷新,刷新成功后重试请求
 */
internal class RefreshTokenInterceptor : Interceptor {
    companion object {
        private const val TAG = "Token"
    }

    private val lock = Any()

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        //判断Token是否过期
        if (response.code() == 401) {
            //通过同步锁和双重校验限制多个请求同时返回401时，只有第一个请求能执行刷新token的操作
            if ((!TextUtils.isEmpty(TokenSingleton.instance.accessToken)) &&
                TokenSingleton.instance.accessToken == request.header("accessToken")
            ) {
                synchronized(lock) {
                    if (!TextUtils.isEmpty(TokenSingleton.instance.accessToken) && TokenSingleton
                            .instance.accessToken == request.header("accessToken")
                    ) {
                        refreshToken(object : RefreshTokenCallBack {
                            override fun onFail(response: Response): Response {
                                Log.d(TAG, "token刷新失败-------")
                                //直接返回刷新token过期response.code=401的结果，应用层做重新登录的逻辑
                                return response
                            }

                            override fun onSuccess() {
                                Log.d(TAG, "token刷新成功----重试业务请求---")
                            }
                        })
                    }
                }
            }

            //使用新的AccessToken继续业务请求
            if (!TextUtils.isEmpty(TokenSingleton.instance.accessToken) &&
                TokenSingleton.instance.accessToken != request.header("accessToken")
            ) {
                Log.e(TAG, "获得新Token后重试")
                val newRequest = request.newBuilder()
                    .header("accessToken", TokenSingleton.instance.accessToken)
                    .build()
                //继续业务请求
                return chain.proceed(newRequest)
            } else {
                throw RefreshTokenFailException("refresh token fail")
            }
        }
        return response
    }

    /**
     * 同步请求刷新Token
     */
    private fun refreshToken(callback: RefreshTokenCallBack) {
        Log.d(TAG, "进入刷新Token。。。。。")
        val refreshToken = TokenSingleton.instance.refreshToken
        if (TextUtils.isEmpty(refreshToken)) {
            Log.d(TAG, "刷新Token失败！")
            throw RefreshTokenFailException("refreshToken is empty,please first login")
        }
        val formBody = FormBody.Builder().build()
        val request = Request.Builder()
            .url("http://192.168.3.8:8083/accessToken/refresh")
            .addHeader("refreshToken", refreshToken)
            .post(formBody)
            .build()

        val call = OkHttpClient.Builder().build().newCall(request)
        val response = call.execute()
        val result = response.body()?.string()
        val tokenBean: TokenBean = Gson().fromJson(result, TokenBean::class.java)
        when {
            response.code() == 200 -> {
                Log.d(TAG, "Token刷新成功")
                //刷新token成功，更新token
                TokenSingleton.instance.accessToken = tokenBean.data.accessToken
                TokenSingleton.instance.refreshToken = tokenBean.data.refreshToken
                callback.onSuccess()
            }
            response.code() == 401 -> {
                Log.e(TAG, "Token过期需要自动登录！")
                callback.onFail(response)
            }
            else -> {
                throw RefreshTokenFailException("refresh token fail")
            }
        }
    }

    interface RefreshTokenCallBack {
        fun onFail(response: Response): Response
        fun onSuccess()
    }
}