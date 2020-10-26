package com.cyq.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val client = OkHttpClient()
        val request: Request = Request.Builder()
            .url("https://zhuanlan.zhihu.com/api/columns/zhihuadmin")
            .build();
        client.newCall(request)
            .enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.e("test", "fail ---")
                }

                override fun onResponse(call: Call, response: Response) {
                    Log.e("test", "success --->${response.body}")
                }
            })
    }
}