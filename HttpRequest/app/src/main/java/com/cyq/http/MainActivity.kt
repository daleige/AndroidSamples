package com.cyq.http

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cyq.http.bean.BaseBean
import com.cyq.http.bean.TokenBean
import com.cyq.http.bean.UserInfo
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException


class MainActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        private const val TAG = "Token"
    }

    private var tokenEffective = false;
    private lateinit var okHttpClient: OkHttpClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnRegister.setOnClickListener(this)
        btnLogin.setOnClickListener(this)
        btnInfo.setOnClickListener(this)
        btnRegister.setOnClickListener(this)
        btnRefreshToken.setOnClickListener(this)
        btnTest.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        okHttpClient = OkHttpClient.Builder()
            //添加监听Token过期自动刷新的拦截器
            .addInterceptor(RefreshTokenInterceptor())
            .build()

        when (view?.id) {
            R.id.btnRegister -> register("zhangsan", "123456")
            R.id.btnLogin -> login("zhangsan", "123456")
            R.id.btnRefreshToken -> refreshToken()
            R.id.btnInfo -> getUserInfo()
            R.id.btnTest -> testRefreshToken()
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
            .url("http://192.168.3.8:8083/register")
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
            .url("http://192.168.3.8:8083/login")
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
                    TokenSingleton.instance.accessToken = tokenBean.data.accessToken
                    TokenSingleton.instance.refreshToken = tokenBean.data.refreshToken
                    tokenEffective = true
                }
            }
        })
    }

    /**
     * 刷新token
     */
    private fun refreshToken() {
        val refreshToken = TokenSingleton.instance.refreshToken
        if (TextUtils.isEmpty(refreshToken)) {
            Toast.makeText(this, "请先登录！", Toast.LENGTH_SHORT).show()
            return
        }
        val formBody = FormBody.Builder().build()
        val request = Request.Builder()
            .url("http://192.168.3.8:8083/accessToken/refresh")
            .addHeader("refreshToken", refreshToken)
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
                    TokenSingleton.instance.accessToken = tokenBean.data.accessToken
                    TokenSingleton.instance.refreshToken = tokenBean.data.refreshToken
                    tokenEffective = true
                } else if (tokenBean.code == 401) {
                    Log.e(TAG, "Token过期需要自动登录！")
                    showToast("refreshToken过期，需要重新登录！")
                    tokenEffective = false
                }
            }
        })
    }

    /**
     * 获取用户信息
     */
    private fun getUserInfo() {
        val accessToken = TokenSingleton.instance.accessToken
        if (TextUtils.isEmpty(accessToken)) {
            showToast("accessToken为空，请先登录！")
            return
        }
        val formBody = FormBody.Builder().build()
        val request = Request.Builder()
            .url("http://192.168.3.8:8083/userinfo")
            .addHeader("accessToken", accessToken)
            .post(formBody)
            .build()

        val call = okHttpClient.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d(TAG, "获取用户信息失败")
                tokenEffective = false
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val result = response.body()?.string()
                val userInfo: UserInfo = Gson().fromJson(result, UserInfo::class.java)
                if (userInfo.code == 401) {
                    tokenEffective=false
                    Log.e(TAG, "token过期----${response.code()},自动刷新token")
                } else {
                    Log.d(TAG, "成功获取用户信息：${Gson().toJson(userInfo.data)}")
                }
            }
        })
    }

    private fun sum() {
        val accessToken = TokenSingleton.instance.accessToken
        if (TextUtils.isEmpty(accessToken)) {
            showToast("accessToken为空，请先登录！")
            return
        }
        val formBody = FormBody.Builder()
            .add("data1", 12.toString())
            .add("data2", 43.toString())
            .build()
        val request = Request.Builder()
            .url("http://192.168.3.8:8083/sum")
            .addHeader("accessToken", accessToken)
            .post(formBody)
            .build()
        val call = okHttpClient.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d(TAG, "sum请求失败！")
                tokenEffective = false
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val result = response.body()?.string()
                val bean: BaseBean = Gson().fromJson(result, BaseBean::class.java)
                if (bean.code == 401) {
                    tokenEffective=false
                    Log.e(TAG, "sum：token过期----${response.code()},自动刷新token")
                } else {
                    Log.d(TAG, "sum成功：$result")
                }
            }
        })
    }

    private fun multiply() {
        val accessToken = TokenSingleton.instance.accessToken
        if (TextUtils.isEmpty(accessToken)) {
            showToast("accessToken为空，请先登录！")
            return
        }
        val formBody = FormBody.Builder()
            .add("data1", 12.toString())
            .add("data2", 43.toString())
            .build()
        val request = Request.Builder()
            .url("http://192.168.3.8:8083/multiply")
            .addHeader("accessToken", accessToken)
            .post(formBody)
            .build()
        val call = okHttpClient.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d(TAG, "multiply请求失败！")
                tokenEffective = false
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val result = response.body()?.string()
                val bean: BaseBean = Gson().fromJson(result, BaseBean::class.java)
                if (bean.code == 401) {
                    tokenEffective=false
                    Log.e(TAG, "multiply：token过期----${response.code()},自动刷新token")
                } else {
                    Log.d(TAG, "multiply成功：$result")
                }
            }
        })
    }


    /**
     * 轮训接口，测试
     */
    private fun testRefreshToken() {
        Thread {
            while (tokenEffective) {
                Thread.sleep(10_000)
                getUserInfo()
                sum()
                multiply()
            }
        }.start()
    }

    private fun showToast(text: String) {
        runOnUiThread {
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
        }
    }
}
