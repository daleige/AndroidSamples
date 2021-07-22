package com.chenyangqi.nested.scroll.coordinator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chenyangqi.nested.scroll.R

class MainActivity : AppCompatActivity() {
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mData: MutableList<String> = ArrayList()
        for (i in 1..10000) {
            mData.add("item = $i")
        }

        mRecyclerView = findViewById(R.id.mRecyclerView)
        mAdapter = MyAdapter(mData)
        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    inner class MyAdapter(private val data: MutableList<String>) :
        RecyclerView.Adapter<MyViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val view = LayoutInflater.from(this@MainActivity)
                .inflate(R.layout.item_nested_list, parent, false)
            return MyViewHolder(view)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.tvItemContent.text = data[position]
        }

        override fun getItemCount(): Int {
            return data.size
        }
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvItemContent: TextView = itemView.findViewById(R.id.tvItemContent)
    }
}