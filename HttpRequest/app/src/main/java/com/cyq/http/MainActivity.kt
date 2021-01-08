package com.cyq.http

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val TAG = "MainActivity"
    private val REGISTER_URL = "http://127.0.0.1:8083/register?account=zhangsan&password=123456"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnRegister.setOnClickListener(this)
        btnLogin.setOnClickListener(this)
        btnGetInfo.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnRegister -> {
                register("zhangsan", "123456")
            }
            R.id.btnLogin -> {

            }
            R.id.btnGetInfo -> {

            }
        }
    }

    /**
     * 注册
     */
    private fun register(username: String, password: String) {
        val okHttpClient = OkHttpClient.Builder().build()
        val request = Request.Builder()
                .url(REGISTER_URL)
                .get()
                .build()
        val call = okHttpClient.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d(TAG, "fail ---")
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                Log.d(TAG, "success-->" + response.body().toString())
            }
        })
    }

    /**
     * 注册
     */
    private fun login(username: String, password: String) {

    }

    /**
     * 获取用户信息
     */
    private fun getUserInfo(username: String) {

    }

    private fun showToast(text: String) {
        runOnUiThread {
            Toast.makeText(this@MainActivity, text, Toast.LENGTH_SHORT).show()
        }
    }
}