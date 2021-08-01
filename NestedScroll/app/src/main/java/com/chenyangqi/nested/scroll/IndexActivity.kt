package com.chenyangqi.nested.scroll

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chenyangqi.nested.scroll.demo1.Demo1Activity
import com.chenyangqi.nested.scroll.demo3.Demo3Activity
import com.chenyangqi.nested.scroll.demo2.Demo2Activity
import com.chenyangqi.nested.scroll.nesting.NesstingScrollActivity
import kotlinx.android.synthetic.main.activity_index.*

class IndexActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)

        button1.setOnClickListener {
            openActivity(NesstingScrollActivity::class.java)
        }

        button2.setOnClickListener {

        }

        button3.setOnClickListener {
            openActivity(Demo1Activity::class.java)
        }

        button4.setOnClickListener {
            openActivity(Demo2Activity::class.java)
        }

        button5.setOnClickListener {
            openActivity(Demo3Activity::class.java)
        }
    }

    private fun openActivity(clazz: Class<*>) {
        startActivity(Intent(this, clazz))
    }
}