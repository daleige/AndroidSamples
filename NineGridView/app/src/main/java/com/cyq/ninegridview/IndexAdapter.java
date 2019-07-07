package com.cyq.ninegridview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cyq.ninegridview.nine_grid.NineGridView;

import java.util.List;

/**
 * Create by 陈扬齐
 * Create on 2019-07-07
 * description:
 */
public class IndexAdapter extends RecyclerView.Adapter<IndexAdapter.MyViewHolder> {
    private Context mContext;
    private List<List<String>> datas;

    public IndexAdapter(Context mContext, List<List<String>> dates) {
        this.mContext = mContext;
        this.datas = dates;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.nine_grid_list, viewGroup, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        holder.titleTv.setText((i + 1) % 4 + "任正非：今年挖来的“天才少年”薪酬比谷歌还高");
        //NineGridView设置数据
        holder.nineGridViewRv.setData(datas.get(i));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titleTv;
        NineGridView nineGridViewRv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTv = itemView.findViewById(R.id.tv_title);
            nineGridViewRv = itemView.findViewById(R.id.nine_grid_view);
        }
    }
}
