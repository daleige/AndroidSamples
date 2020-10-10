package com.cyq.jetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.cyq.jetpack.bean.Book
import com.cyq.jetpack.databinding.ActivityDataBindingBinding

class DataBindingActivity : AppCompatActivity() {
    val book: Book by lazy {
        val book = Book("张三", 5, "设计模式之禅")
        book
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityDataBinding: ActivityDataBindingBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_data_binding)
        //activityDataBinding.setVariable(BR.book, book)
        activityDataBinding.book = book

    }
}