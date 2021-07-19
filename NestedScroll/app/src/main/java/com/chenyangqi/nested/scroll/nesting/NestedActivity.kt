package com.chenyangqi.nested.scroll.nesting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chenyangqi.nested.scroll.R

class NestedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nested)

        val mRecyclerView = findViewById<RecyclerView>(R.id.mRecyclerView)
        val mData: MutableList<String> = ArrayList()
        for (i in 1..10000) {
            mData.add("item = $i")
        }
        val adapter = MyAdapter(mData)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = adapter
    }

    inner class MyAdapter(private val data: MutableList<String>) :
        RecyclerView.Adapter<MyViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val view = LayoutInflater.from(this@NestedActivity)
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