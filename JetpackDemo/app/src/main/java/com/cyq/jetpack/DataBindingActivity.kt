package com.cyq.jetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.cyq.jetpack.bean.Book
import com.cyq.jetpack.databinding.ActivityDataBindingBinding
import com.cyq.jetpack.event.EventHandleListener

class DataBindingActivity : AppCompatActivity() {
    val book: Book by lazy {
        val book = Book("张三", 5, "设计模式之禅")
        book
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityDataBinding: ActivityDataBindingBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_data_binding)
        //方法一，设置实体类
        //activityDataBinding.setVariable(BR.book, book)
        //方法二：设置实体类
        activityDataBinding.book = book
        //设置响应事件
        activityDataBinding.eventHandler = EventHandleListener(this)
    }
}