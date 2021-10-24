package com.chenyangqi.rxjava

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chenyangqi.rxjava.创建型操作符.CreateTypeActivity
import com.chenyangqi.rxjava.变换型操作符.TransfActivity
import com.chenyangqi.rxjava.条件型操作符.ConditionActivity
import com.chenyangqi.rxjava.过滤型操作符.FilterActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCreateType.setOnClickListener {
            startActivity(Intent(this, CreateTypeActivity::class.java))
        }
        btnTransfType.setOnClickListener {
            startActivity(Intent(this, TransfActivity::class.java))
        }
        btnFilterType.setOnClickListener {
            startActivity(Intent(this, FilterActivity::class.java))
        }
        btnConditionType.setOnClickListener {
            startActivity(Intent(this, ConditionActivity::class.java))

        }
    }
}