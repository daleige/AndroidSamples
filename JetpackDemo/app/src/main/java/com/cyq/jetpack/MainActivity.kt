package com.cyq.jetpack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import com.cyq.jetpack.navigation.NavigationActivity
import com.cyq.jetpack.paging.PageKeyedActivity
import com.cyq.jetpack.paging.PositionalPagingActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDataBinding.setOnClickListener(this)
        btnPaging.setOnClickListener(this)
        btnPaging2.setOnClickListener(this)
        btnNavigation.setOnClickListener(this)

        val msg = Message()
        msg.what = 101
        Log.e("test","begin:${System.currentTimeMillis()}")
        handler.sendMessageDelayed(msg, 5000)
        thread {
            Thread.sleep(1000)
            handler.obtainMessage()
        }
        btnThread.setOnClickListener(this)
    }

   private val handler = Handler(object : Handler.Callback {
        override fun handleMessage(msg: Message): Boolean {
            Log.e("test","end:${System.currentTimeMillis()}")
            Log.e("test", "---handler---")
            return true
        }
    })

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnDataBinding ->
                startActivity(Intent(this, DataBindingActivity::class.java))
            R.id.btnPaging ->
                startActivity(Intent(this, PositionalPagingActivity::class.java))
            R.id.btnPaging2 ->
                startActivity(Intent(this, PageKeyedActivity::class.java))
            R.id.btnThread ->
                startActivity(Intent(this, ThreadActivity::class.java))
            R.id.btnNavigation ->
                startActivity(Intent(this,NavigationActivity::class.java))
        }
    }
}