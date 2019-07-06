package com.cyq.ninegridview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Create by 陈扬齐
 * Create on 2019-07-07
 * description:
 */
public class IndexAdapter extends RecyclerView.Adapter<IndexAdapter.MyViewHolder> {
    private Context mContext;
    private List<List<String>> dates;

    public IndexAdapter(Context mContext, List<List<String>> dates) {
        this.mContext = mContext;
        this.dates = dates;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.nine_grid_list, viewGroup, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        holder.titleTv.setText(i + 1 + "张图片");
    }

    @Override
    public int getItemCount() {
        return dates.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titleTv;
        RecyclerView nineGridViewRv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTv = itemView.findViewById(R.id.tv_title);
            nineGridViewRv = itemView.findViewById(R.id.rv_nine_grid);
        }
    }
}
