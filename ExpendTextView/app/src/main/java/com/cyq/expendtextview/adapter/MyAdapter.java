package com.cyq.expendtextview.adapter;

import android.content.Context;
import android.os.Build;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.cyq.expendtextview.R;
import com.cyq.expendtextview.bean.TestBean;
import com.cyq.expendtextview.view.MyExpendTextView;

import java.util.List;

/**
 * @author : ChenYangQi
 * date   : 2019/12/27 14:59
 * desc   :
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context mContext;
    private List<TestBean> mList;
    private int widht;

    public MyAdapter(Context mContext, List<TestBean> mList, int width) {
        this.mContext = mContext;
        this.mList = mList;
        this.widht = width;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_layout_list, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        MyExpendTextView mExpendTextView = holder.mTextView;
        String str = mList.get(position).getStr();
        StaticLayout staticLayout = StaticLayout.Builder
                .obtain(str, 0, str.length(), mExpendTextView.getPaint(), widht)
                .build();
        Log.i("test", "staticLayout line count:" + staticLayout.getLineCount());
        int currentLine = staticLayout.getLineCount();
        int maxHeight = staticLayout.getHeight();
        int lineHeight = maxHeight / currentLine;
        int minHeight = lineHeight * 3;
        mExpendTextView.init(minHeight, maxHeight);
        if (currentLine >= 3) {
            mExpendTextView.getLayoutParams().height = minHeight;
        }

        if (mList.get(position).isChecked) {

        } else {

        }

        mExpendTextView.setOpneAndCloseTaggerListener(new MyExpendTextView.OnOpenAndCloseTaggerListener() {
            @Override
            public void open() {
                mList.get(position).isChecked = true;
            }

            @Override
            public void close() {
                mList.get(position).isChecked = false;
            }
        });

        mExpendTextView.setText(mList.get(position).getStr());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private MyExpendTextView mTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tv_my);
        }
    }
}
