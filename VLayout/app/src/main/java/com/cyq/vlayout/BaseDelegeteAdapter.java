package com.cyq.vlayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;

/**
 * Create by 陈扬齐
 * Create on 2019-07-06
 * description:
 */
public class BaseDelegeteAdapter extends DelegateAdapter.Adapter<BaseViewHolder> {
    private LayoutHelper mLayoutHelper;
    private int mCount = -1;
    private int mLayoutId = -1;
    private Context mContext;
    private int mViewTypeItem = -1;

    public BaseDelegeteAdapter(Context context,
                               LayoutHelper layoutHelper,
                               int layoutId, int count) {
        this.mLayoutHelper = layoutHelper;
        this.mCount = count;
        this.mLayoutId = layoutId;
        this.mContext = context;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new BaseViewHolder(LayoutInflater.from(mContext).inflate(mLayoutId, viewGroup,
                false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return mCount;
    }
}
