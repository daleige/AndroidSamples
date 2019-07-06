package com.cyq.ninegridview.nine_grid;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.cyq.ninegridview.R;

import java.util.List;

/**
 * Create by 陈扬齐
 * Create on 2019-07-07
 * description:
 */
public class TypeTowAdapter extends RecyclerView.Adapter<TypeTowAdapter.TypeTowViewHolder> {
    private Context mContext;
    private List<String> list;

    public TypeTowAdapter(Context mContext, List<String> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public TypeTowViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.grid_img_square, viewGroup,
                false);
        return new TypeTowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TypeTowViewHolder holder, int i) {
        Glide.with(mContext).load(list.get(i)).into(holder.squareImageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class TypeTowViewHolder extends RecyclerView.ViewHolder {
        SquareImageView squareImageView;

        public TypeTowViewHolder(@NonNull View itemView) {
            super(itemView);
            squareImageView = itemView.findViewById(R.id.iv_square);
        }
    }
}
