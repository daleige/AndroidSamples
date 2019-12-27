package com.cyq.expendtextview.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.cyq.expendtextview.R;

import java.util.List;

/**
 * @author : ChenYangQi
 * date   : 2019/12/27 13:25
 * desc   :
 */
public class TestAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public TestAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String s) {
        baseViewHolder.setText(R.id.tv_my, s);
    }
}
