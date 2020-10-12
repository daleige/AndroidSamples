package com.cyq.jetpack.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.cyq.jetpack.R
import com.cyq.jetpack.databinding.RecyclerviewListBinding
import com.cyq.jetpack.model.BookBean

/**
 * @author : ChenYangQi
 * date   : 2020/10/11 18:26
 * desc   : RecyclerView适配器
 */
class RecyclerViewAdapter(books: List<BookBean>) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    private val bookBeans: List<BookBean> = books

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val recyclerviewListBinding: RecyclerviewListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recyclerview_list,
            parent,
            false
        )
        return MyViewHolder(recyclerviewListBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.recyclerviewListBinding.bookBean = bookBeans[position]
//        if (position % 2 == 0) {
//            holder.recyclerviewListBinding.tvAuthor.visibility = View.GONE
//        }else{
//            holder.recyclerviewListBinding.tvAuthor.visibility = View.VISIBLE
//        }
    }

    override fun getItemCount(): Int {
        return bookBeans.size
    }

    class MyViewHolder(itemView: RecyclerviewListBinding) : RecyclerView.ViewHolder(itemView.root) {
        val recyclerviewListBinding = itemView
    }
}
