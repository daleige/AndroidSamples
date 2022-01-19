package com.chenyangqi.listadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * @describe
 * @author chenyangqi
 * @time 2022/1/19 17:49
 */
class MyAdapter {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        companion object {
            fun onCreate(parent: ViewGroup): MyViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return MyViewHolder(view)
            }
        }
    }
}