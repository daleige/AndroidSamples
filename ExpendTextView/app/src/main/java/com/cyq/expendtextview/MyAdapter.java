package com.cyq.expendtextview;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * @author : ChenYangQi
 * date   : 2019/12/21 9:22
 * desc   : 列表Adapter
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context mContext;
    private List<DateBean> mList;

    public MyAdapter(Context mContext, List<DateBean> mList) {
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
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final MyExpendTextView mExpendTextView = holder.mExpendTextView;
        mExpendTextView.setEllipsize(TextUtils.TruncateAt.END);
        mExpendTextView.setText(mList.get(position).getStr());
        if (mList.get(position).getExpend()) {
            mExpendTextView.setExpend(true);
        } else {
            mExpendTextView.setExpend(false);
        }
        mExpendTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mList.get(position).getExpend()) {
                    mList.get(position).setExpend(false);
                    mExpendTextView.setExpend(false);
                } else {
                    mList.get(position).setExpend(true);
                    mExpendTextView.setExpend(true);
                }
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
