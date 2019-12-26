package com.cyq.jdtoolbardemo;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Time: 2019-12-26 22:55
 * Author: ChenYangQi
 * Description:
 */
public class MyAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public MyAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }


}
