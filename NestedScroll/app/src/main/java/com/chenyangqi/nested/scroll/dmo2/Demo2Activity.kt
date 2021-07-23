package com.chenyangqi.nested.scroll.dmo2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.chenyangqi.nested.scroll.R
import com.chenyangqi.nested.scroll.adapter.MyAdapter
import kotlinx.android.synthetic.main.activity_demo2.*

class Demo2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo2)

        val mData: MutableList<String> = ArrayList()
        for (i in 1..100) {
            mData.add("item = $i")
        }

        val adapter = MyAdapter(this, mData)
        mRecyclerView.adapter = adapter
        mRecyclerView.layoutManager = LinearLayoutManager(this)

    }
}