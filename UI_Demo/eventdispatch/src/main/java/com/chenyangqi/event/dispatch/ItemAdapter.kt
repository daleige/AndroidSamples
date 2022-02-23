package com.chenyangqi.event.dispatch

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ItemAdapter(context: Context, private val layoutId: Int, list: List<String>) :
    ArrayAdapter<String>(context, layoutId, list) {
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(layoutId, parent, false)
        val tvItem = view.findViewById<TextView>(R.id.tvItem)
        tvItem.text = getItem(position)
        return view
    }
}