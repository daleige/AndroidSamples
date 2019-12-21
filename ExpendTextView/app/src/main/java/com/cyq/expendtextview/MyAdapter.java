package com.cyq.expendtextview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : ChenYangQi
 * date   : 2019/12/21 9:22
 * desc   : 列表Adapter
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context mContext;
    private List<String> mList = new ArrayList<>();

    public MyAdapter(Context mContext, List<String> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_tv_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final MyExpendTextView mExpendTextView = holder.mExpendTextView;
        mExpendTextView.setText(mList.get(position));
        mExpendTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExpendTextView.setCurrentLines();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        MyExpendTextView mExpendTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mExpendTextView = itemView.findViewById(R.id.expand_tv);
        }
    }
}
