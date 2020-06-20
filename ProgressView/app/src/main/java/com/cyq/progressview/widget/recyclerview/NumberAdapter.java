package com.cyq.progressview.widget.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cyq.progressview.R;

/**
 * @author : ChenYangQi
 * date   : 2020/6/20 10:28
 * desc   :
 */
public class NumberAdapter extends RecyclerView.Adapter<NumberAdapter.MyViewHolder> {
    private final String[] mNumbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private Context mContext;

    public NumberAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_number_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mTvNumber.setText(mNumbers[position]);
    }

    @Override
    public int getItemCount() {
        return mNumbers.length;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvNumber;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvNumber = itemView.findViewById(R.id.mTvNumber);
        }
    }
}
