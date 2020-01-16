package com.cyq.customview.flowLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

/**
 * @author : ChenYangQi
 * date   : 2020/1/16 9:30
 * desc   :
 */
public class TabFlowLayout extends FlowLayout {
    private TagAdapter mAdapter;

    public TabFlowLayout(Context context) {
        this(context, null);
    }

    public TabFlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setAdapter(TagAdapter tagAdapter) {
        mAdapter = tagAdapter;
        onDataChanged();
    }

    private void onDataChanged() {
        removeAllViews();
        TagAdapter adapter = mAdapter;
        for (int i = 0; i < adapter.getItemCount(); i++) {
            View view = adapter.createView(LayoutInflater.from(getContext()), this, i);
            adapter.bindView(view, i);
            addView(view);
        }
    }
}
