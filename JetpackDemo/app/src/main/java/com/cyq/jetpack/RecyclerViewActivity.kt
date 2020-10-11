package com.cyq.jetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.cyq.jetpack.adapter.RecyclerViewAdapter
import com.cyq.jetpack.databinding.ActivityRecyclerViewBinding
import com.cyq.jetpack.model.BookBean

class RecyclerViewActivity : AppCompatActivity() {
    private val imgPath = "https://img11.360buyimg.com/n2/jfs/t17389/179/1726927093/183483/b97d85e4/5ad5c1c4Nc58dded6.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val recyclerViewBinding: ActivityRecyclerViewBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_recycler_view)

        //模拟数据
        val bookBeans = mutableListOf<BookBean>()
        for (i in 1..40) {
            val bookBean = BookBean("设计模式之禅$i", imgPath, "张三$i")
            bookBeans.add(bookBean)
        }

        recyclerViewBinding.recyclerview.layoutManager = LinearLayoutManager(this)
        val adapter = RecyclerViewAdapter(bookBeans)
        recyclerViewBinding.recyclerview.adapter = adapter
    }
}