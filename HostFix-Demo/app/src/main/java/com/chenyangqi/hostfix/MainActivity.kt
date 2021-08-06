package com.chenyangqi.hostfix

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //调用一个存在异常的类方法
        button1.setOnClickListener {
            Toast.makeText(
                MainActivity@ this,
                "字符串长度=${TobeFix.getStrLength(null)}",
                Toast.LENGTH_SHORT
            ).show()
        }

        button2.setOnClickListener {

        }
    }
}