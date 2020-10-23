package com.cyq.jetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.provider.Settings
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.cyq.jetpack.databinding.ActivityBothBindingBinding
import com.cyq.jetpack.view_model.TowWayBindingViewModel
import com.cyq.jetpack.view_model.TowWayBindingViewModel2
import kotlinx.android.synthetic.main.activity_both_binding.*
import kotlin.concurrent.thread

/**
 * 双向绑定
 */
class BothBindingActivity : AppCompatActivity() {
    private val towWayBindingViewModel = TowWayBindingViewModel()
    private val towWayBindingViewModel2 = TowWayBindingViewModel2()

    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityBothBindingBinding: ActivityBothBindingBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_both_binding)
        activityBothBindingBinding.viewModel = towWayBindingViewModel
        activityBothBindingBinding.viewModel2 = towWayBindingViewModel2

        btnChangeViewModel.setOnClickListener {
            towWayBindingViewModel.setUserName("张三丰")
            towWayBindingViewModel.setPassword("888888")

            towWayBindingViewModel2.setUserName("利斯够")
            towWayBindingViewModel2.setPassword("7787787")
        }
//
//        thread {
//            Looper.prepare()
//            Handler(object : Handler.Callback {
//                override fun handleMessage(msg: Message): Boolean {
//
//                    return true
//                }
//
//            })
//        }
        val msg = Message()
        msg.what = 101
        handler.sendMessageDelayed(msg, 5000)
        thread {
            Thread.sleep(1000)
            handler.obtainMessage()
        }
    }

    val handler = Handler(object : Handler.Callback {
        override fun handleMessage(msg: Message): Boolean {
            Log.e("test", "---handler---")
            return true
        }
    })

    
}