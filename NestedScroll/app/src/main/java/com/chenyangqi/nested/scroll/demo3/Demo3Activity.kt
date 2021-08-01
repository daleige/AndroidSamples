package com.chenyangqi.nested.scroll.demo3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.chenyangqi.nested.scroll.R
import com.chenyangqi.nested.scroll.MyAdapter
import kotlinx.android.synthetic.main.activity_demo3.*

class Demo3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo3)
        val mData: MutableList<String> = ArrayList()
        for (i in 1..100) {
            mData.add("item = $i")
        }
        val adapter = MyAdapter(this, mData)
        mRecyclerView.adapter = adapter
        mRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}