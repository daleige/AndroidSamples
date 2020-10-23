package com.cyq.jetpack

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.cyq.jetpack.model.Book
import com.cyq.jetpack.databinding.ActivityDataBindingBinding
import com.cyq.jetpack.event.EventHandleListener
import kotlinx.android.synthetic.main.activity_data_binding.*

class DataBindingActivity : AppCompatActivity() {
    private val book = Book("张三", 5, "设计模式之禅")
    private val imgUrl = "http://p4.music.126.net/SFXsIYI3hCCedvbqrvVytA==/109951165335411992.jpg"

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
        //自定义加载图片的BindingAdapter
        activityDataBinding.networkImage = imgUrl
        //多参数重载的BindingAdapter
        activityDataBinding.imageUrl = ""
        activityDataBinding.defaultImage=R.mipmap.ic_launcher

    }
}