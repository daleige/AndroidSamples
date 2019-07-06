package com.cyq.ninegridview.nine_grid;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.cyq.ninegridview.R;

/**
 * Create by 陈扬齐
 * Create on 2019-07-07
 * description:
 */
public class TypeOneAdapter extends RecyclerView.Adapter<TypeOneAdapter.TypeOneViewHolder> {
    private Context mContext;
    private String path;

    public TypeOneAdapter(Context mContext, String path) {
        this.mContext = mContext;
        this.path = path;
    }

    @NonNull
    @Override
    public TypeOneViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.grid_img_auto_size, viewGroup,
                false);
        return new TypeOneViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TypeOneViewHolder holder, int i) {
        Glide.with(mContext).load(path).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class TypeOneViewHolder extends RecyclerView.ViewHolder {
        AutoSizeImageView imageView;

        public TypeOneViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_auto_size);
        }
    }
}
