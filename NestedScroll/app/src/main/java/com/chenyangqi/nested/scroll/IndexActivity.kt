package com.chenyangqi.nested.scroll

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chenyangqi.nested.scroll.nesting.NesstingScrollActivity
import kotlinx.android.synthetic.main.activity_index.*

class IndexActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)

        button1.setOnClickListener {
            startActivity(Intent(this,NesstingScrollActivity::class.java))
        }

        button2.setOnClickListener {
            //startActivity(Intent(this,NesstingScrollActivity::class.java))
        }
    }
}