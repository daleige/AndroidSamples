package com.cyq.retrofit

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cyq.lib_network.RetrofitManager
import com.cyq.lib_network.RspEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RetrofitManager.createReq(RequestAPI::class.java)
            .getPersonInfo(1231231, "张安")
            .enqueue(object : Callback<RspEntity<Person>> {
                override fun onResponse(
                    call: Call<RspEntity<Person>>, response: Response<RspEntity<Person>>
                ) {
                    Toast.makeText(this@MainActivity, "请求成功", Toast.LENGTH_SHORT).show()
                    Log.d("test", "请求成功：" + response.toString())
                }

                override fun onFailure(call: Call<RspEntity<Person>>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "请求失败", Toast.LENGTH_SHORT).show()
                    Log.d("test", "请求失败！")
                    t.printStackTrace()
                }
            })
    }
}