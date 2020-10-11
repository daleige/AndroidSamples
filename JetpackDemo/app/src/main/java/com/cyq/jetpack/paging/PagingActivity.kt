package com.cyq.jetpack.paging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cyq.jetpack.R
import com.cyq.jetpack.paging.api.RetrofitClient
import com.cyq.jetpack.paging.model.Movies
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PagingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging)

        CoroutineScope(Dispatchers.Main).launch {
            val call = RetrofitClient.getInstance()
                .getApi()
                .getMovies(0, 8)
//                .enqueue(object : Callback<Movies> {
//                    override fun onFailure(call: Call<Movies>, t: Throwable) {
//                        println("http请求失败")
//                        t.printStackTrace()
//                    }
//
//                    override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
//                        println("http请求成功：${response.code()} \t ${response.body()}")
//                    }
//                })
            println("请求结果：" + Gson().toJson(call))
        }
    }
}