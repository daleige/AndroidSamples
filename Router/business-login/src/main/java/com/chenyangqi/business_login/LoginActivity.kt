package com.chenyangqi.business_login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chenyangqi.router.annotations.Destination

@Destination(url = "router://page_login", description = "登录页面")
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}