package com.cyq.http

import android.util.Log
import okhttp3.*
import java.io.IOException

/**
 * @author chenyq113@midea.com
 * @describe xxx
 * @time 2021/1/8 14:44
 */
object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val okHttpClient = OkHttpClient.Builder().build()
        val body:FormBody=FormBody.Builder().add("","").build()
        val request = Request.Builder()
            .url("http://baidu.com")
            .post(body)
            .build()
        val call = okHttpClient.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("test", "fail ---")
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                Log.e("test", "success-->" + response.body())
            }
        })
    }
}