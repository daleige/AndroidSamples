package com.cyq.expendtextview.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cyq.expendtextview.R;
import com.cyq.expendtextview.bean.DateBean;
import com.cyq.expendtextview.test.ExpandableTextView;

import java.util.List;

/**
 * @author : ChenYangQi
 * date   : 2019/12/21 9:22
 * desc   : 列表Adapter
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context mContext;
    private List<DateBean> mList;
    private Activity mActivity;

    public MyAdapter(Context mContext, Activity activity, List<DateBean> mList) {
        this.mContext = mContext;
        this.mActivity = activity;
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
        final ExpandableTextView expandableTextView = holder.mExpendTextView;
        int viewWidth =
                mActivity.getWindowManager().getDefaultDisplay().getWidth() - dp2px(mContext, 20f);
        expandableTextView.initWidth(viewWidth);
        expandableTextView.setMaxLines(3);
        expandableTextView.setHasAnimation(false);
        expandableTextView.setCloseInNewLine(false);
        expandableTextView.setOpenSuffixColor(Color.parseColor("#D81B60"));
        expandableTextView.setCloseSuffixColor(Color.parseColor("#D81B60"));
        expandableTextView.setOriginalText(mList.get(position).getStr());
        expandableTextView.setOpenAndCloseCallback(new ExpandableTextView.OpenAndCloseCallback() {
            @Override
            public void onOpen() {
                expandableTextView.setHasAnimation(false);
                Log.i("test", "open");
            }

            @Override
            public void onClose() {
                expandableTextView.setHasAnimation(false);
                Log.i("test", "close");
            }
        });
        if(mList.get(position).getExpend()){
            expandableTextView.open();
        }else {
            expandableTextView.close();
        }
        Button button = holder.button;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableTextView.setHasAnimation(true);
                if(mList.get(position).getExpend()){
                    expandableTextView.close();
                    mList.get(position).setExpend(false);
                }else {
                    expandableTextView.open();

                    mList.get(position).setExpend(true);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ExpandableTextView mExpendTextView;
        Button button;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mExpendTextView = itemView.findViewById(R.id.expanded_text);
            button = itemView.findViewById(R.id.btn_tagger);
        }
    }

    private int dp2px(Context context, float dpValue) {
        int res;
        final float scale = context.getResources().getDisplayMetrics().density;
        if (dpValue < 0)
            res = -(int) (-dpValue * scale + 0.5f);
        else
            res = (int) (dpValue * scale + 0.5f);
        return res;
    }
}
