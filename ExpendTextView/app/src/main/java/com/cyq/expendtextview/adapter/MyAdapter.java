package com.cyq.expendtextview.adapter;

import android.content.Context;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextUtils;
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
    private int width;

    public MyAdapter(Context mContext, List<TestBean> mList, int width) {
        this.mContext = mContext;
        this.mList = mList;
        this.width = width;
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
        final MyExpendTextView mExpendTextView = holder.mTextView;
        String str = mList.get(position).getStr();
//        StaticLayout staticLayout = StaticLayout.Builder
//                .obtain(str, 0, str.length(), mExpendTextView.getPaint(), width)
//                .build();
        StaticLayout staticLayout = new StaticLayout(str, mExpendTextView.getPaint(), width, Layout.Alignment.ALIGN_NORMAL,
                1, 0, false);
        int currentLine = staticLayout.getLineCount();
        int maxHeight = staticLayout.getHeight();
        int lineHeight = maxHeight / currentLine;

        if (currentLine > 3) {
            int minHeight = lineHeight * 3;
            mExpendTextView.init(minHeight, maxHeight, true);
            mExpendTextView.setEllipsize(TextUtils.TruncateAt.END);
            mExpendTextView.getLayoutParams().height = minHeight;
            if (mList.get(position).isChecked) {
                mExpendTextView.setMaxLines(Integer.MAX_VALUE);
            } else {
                mExpendTextView.setMaxLines(3);
            }
        } else {
            mExpendTextView.init(maxHeight, maxHeight, false);
        }

        if (mList.get(position).isChecked) {
            mExpendTextView.open();
        } else {
            mExpendTextView.close();
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
