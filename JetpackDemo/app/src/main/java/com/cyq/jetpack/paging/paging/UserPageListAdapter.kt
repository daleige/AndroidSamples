package com.cyq.jetpack.paging.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cyq.jetpack.R
import com.cyq.jetpack.databinding.UserItemBinding
import com.cyq.jetpack.paging.model.Item

/**
 *    @author : ChenYangQi
 *    date   : 2020/10/12 16:39
 *    desc   :
 */
class UserPageListAdapter :
    PagedListAdapter<Item, UserPageListAdapter.UserViewHolder>(DIFF_CALLBACK) {
    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Item> =
            object : DiffUtil.ItemCallback<Item>() {
                /**
                 * 判断两个对象是否代表同一个Item
                 */
                override fun areItemsTheSame(
                    oldItem: Item,
                    newItem: Item
                ): Boolean {
                    return oldItem.user_id == newItem.user_id
                }

                /**
                 * 判断两个Item是否存在不一样的数据
                 */
                override fun areContentsTheSame(
                    oldItem: Item,
                    newItem: Item
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val userItemBinding = DataBindingUtil.inflate<UserItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.user_item,
            parent, false
        )
        return UserViewHolder(userItemBinding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.userItemBinding.item = getItem(position)
        holder.userItemBinding.root.setOnClickListener {
            Toast.makeText(
                holder.userItemBinding.root.context,
                getItem(position)?.display_name,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    class UserViewHolder(itemView: UserItemBinding) : RecyclerView.ViewHolder(itemView.root) {
        val userItemBinding = itemView
    }
}