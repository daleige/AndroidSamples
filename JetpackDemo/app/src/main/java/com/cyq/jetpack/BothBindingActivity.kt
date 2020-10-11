package com.cyq.jetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.cyq.jetpack.databinding.ActivityBothBindingBinding
import com.cyq.jetpack.view_model.TowWayBindingViewModel
import com.cyq.jetpack.view_model.TowWayBindingViewModel2
import kotlinx.android.synthetic.main.activity_both_binding.*

/**
 * 双向绑定
 */
class BothBindingActivity : AppCompatActivity() {
    val towWayBindingViewModel = TowWayBindingViewModel()
    val towWayBindingViewModel2 = TowWayBindingViewModel2()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityBothBindingBinding: ActivityBothBindingBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_both_binding)
        activityBothBindingBinding.viewModel = towWayBindingViewModel
        activityBothBindingBinding.viewModel2 = towWayBindingViewModel2

        btnChangeViewModel.setOnClickListener {
            towWayBindingViewModel.setUserName("张三丰")
            towWayBindingViewModel.setPassword("888888")

            towWayBindingViewModel2.setUserName("李四狗")
            towWayBindingViewModel2.setPassword("aaaaaa")
        }
    }
}