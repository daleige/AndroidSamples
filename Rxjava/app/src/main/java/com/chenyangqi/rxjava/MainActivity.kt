package com.chenyangqi.rxjava

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chenyangqi.rxjava.创建型操作符.CreateTypeActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCreateType.setOnClickListener {
            startActivity(Intent(this, CreateTypeActivity::class.java))
        }
    }
}