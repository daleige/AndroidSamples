package com.cyq.http

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cyq.http.bean.TokenBean
import com.cyq.http.bean.UserInfo
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = "MainActivity"
    private lateinit var okHttpClient: OkHttpClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnRegister.setOnClickListener(this)
        btnLogin.setOnClickListener(this)
        btnInfo.setOnClickListener(this)
        btnRegister.setOnClickListener(this)
        btnRefreshToken.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        okHttpClient = OkHttpClient.Builder().build()
        when (view?.id) {
            R.id.btnRegister -> register("zhangsan", "123456")
            R.id.btnLogin -> login("zhangsan", "123456")
            R.id.btnRefreshToken -> refreshToken()
            R.id.btnInfo -> getUserInfo("zhangsan")
        }
    }

    /**
     * 注册接口
     */
    private fun register(username: String, password: String) {
        val formBody = FormBody.Builder()
            .add("account", username)
            .add("password", password)
            .build()
        val request = Request.Builder()
            .url("http://192.168.3.9:8083/register")
            .post(formBody)
            .build()
        val call = okHttpClient.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d(TAG, "请求失败：$call")
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                Log.d(TAG, "注册成功----用户：$username")
                showToast("注册成功")
            }
        })
    }

    /**
     * 登录接口
     */
    private fun login(username: String, password: String) {
        val formBody = FormBody.Builder()
            .add("account", username)
            .add("password", password)
            .build()
        val request = Request.Builder()
            .url("http://192.168.3.9:8083/login")
            .post(formBody)
            .build()

        val call = okHttpClient.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d(TAG, "请求失败：$call")
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val result = response.body()?.string()
                Log.d(TAG, "登录成功----用户：$username")
                showToast("登录成功")
                val tokenBean: TokenBean = Gson().fromJson(result, TokenBean::class.java)
                if (tokenBean.code == 200) {
                    TokenManager.getInstance().accessToken = tokenBean.data.accessToken
                    TokenManager.getInstance().refreshToken = tokenBean.data.refreshToken
                }
            }
        })
    }

    /**
     * 刷新token
     */
    private fun refreshToken() {
        val refreshToken = TokenManager.getInstance().refreshToken
        if (TextUtils.isEmpty(refreshToken)) {
            Toast.makeText(this, "请先登录！", Toast.LENGTH_SHORT).show()
            return
        }
        val formBody = FormBody.Builder().add("refreshToken", refreshToken).build()
        val request = Request.Builder()
            .url("http://192.168.3.9:8083/accessToken/refresh")
            .post(formBody)
            .build()

        val call = okHttpClient.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d(TAG, "请求失败：$call")
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val result = response.body()?.string()
                Log.d(TAG, "Token刷新成功----********-------*******")
                val tokenBean: TokenBean = Gson().fromJson(result, TokenBean::class.java)
                if (tokenBean.code == 200) {
                    TokenManager.getInstance().accessToken = tokenBean.data.accessToken
                    TokenManager.getInstance().refreshToken = tokenBean.data.refreshToken
                } else if (tokenBean.code == 401) {
                    Log.e(TAG, "Token过期需要自动登录！")
                    showToast("refreshToken过期，需要重新登录！")
                }
            }
        })

    }

    /**
     * 获取用户信息
     */
    private fun getUserInfo(accessToken: String) {
        val accessToken = TokenManager.getInstance().accessToken
        if (TextUtils.isEmpty(accessToken)) {
            showToast("accessToken为空，请先登录！")
            return
        }
        val formBody = FormBody.Builder().add("accessToken", accessToken).build()
        val request = Request.Builder()
            .url("http://192.168.3.9:8083/userinfo")
            .post(formBody)
            .build()

        val call = okHttpClient.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d(TAG, "获取用户信息失败")
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val result = response.body()?.string()
                val userInfo: UserInfo = Gson().fromJson(result, UserInfo::class.java)
                if (userInfo.code == 401) {
                    Log.e(TAG, "token过期----${response.code()},自动刷新token")
                } else {
                    Log.d(TAG, "成功获取用户信息：${Gson().toJson(userInfo.data)}")
                }
            }
        })

    }

    private fun showToast(text: String) {
        runOnUiThread {
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
        }
    }
}
