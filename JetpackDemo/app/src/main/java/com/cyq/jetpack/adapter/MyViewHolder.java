package com.cyq.jetpack.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cyq.jetpack.databinding.RecyclerviewListBinding;

/**
 * @author : ChenYangQi
 * date   : 2020/10/11 18:54
 * desc   :
 */
public class MyViewHolder extends RecyclerView.ViewHolder {
    RecyclerviewListBinding recyclerviewListBinding;

    public MyViewHolder(RecyclerviewListBinding itemView) {
        super(itemView.getRoot());
        recyclerviewListBinding = itemView;
    }
}
