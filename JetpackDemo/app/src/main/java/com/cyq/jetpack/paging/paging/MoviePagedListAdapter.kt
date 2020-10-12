package com.cyq.jetpack.paging.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cyq.jetpack.R
import com.cyq.jetpack.databinding.MovieItemBinding
import com.cyq.jetpack.paging.model.Subject

/**
 *    @author : ChenYangQi
 *    date   : 2020/10/12 11:31
 *    desc   :
 */
class MoviePagedListAdapter :
    PagedListAdapter<Subject, MoviePagedListAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Subject> =
            object : DiffUtil.ItemCallback<Subject>() {
                /**
                 * 判断两个对象是否代表同一个Item
                 */
                override fun areItemsTheSame(
                    oldItem: Subject,
                    newItem: Subject
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                /**
                 * 判断两个Item是否存在不一样的数据
                 */
                override fun areContentsTheSame(
                    oldItem: Subject,
                    newItem: Subject
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val movieItemBinding = DataBindingUtil.inflate<MovieItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.movie_item,
            parent,
            false
        )
        return MovieViewHolder(movieItemBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.movieItemBinding.subject = getItem(position)
        holder.movieItemBinding.root.setOnClickListener {
            Toast.makeText(
                holder.movieItemBinding.root.context,
                "点击Id:${getItem(position)?.id}",
                Toast.LENGTH_SHORT
            )
                .show()
        }
    }

    class MovieViewHolder(itemView: MovieItemBinding) : RecyclerView.ViewHolder(itemView.root) {
        val movieItemBinding = itemView
    }
}