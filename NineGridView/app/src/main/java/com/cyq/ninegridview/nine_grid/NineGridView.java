package com.cyq.ninegridview.nine_grid;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.cyq.ninegridview.R;

import java.util.List;

/**
 * Create by 陈扬齐
 * Create on 2019-07-07
 * description:自定义九宫格View,基于组合布局
 */
public class NineGridView extends FrameLayout {
    private RecyclerView recyclerView;
    private View placeholderView;

    public NineGridView(Context context) {
        super(context);
        init();
    }

    public NineGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NineGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.nine_grid_layout, this, true);
        recyclerView = findViewById(R.id.rv_nine_grid_list);
        placeholderView = findViewById(R.id.placeholder_view);
    }

    /**
     * 设置数据，初始化Recyclerview
     *
     * @param datas
     */
    public void setData(List<String> datas) {
        GridItemDecoration.Builder builder = new GridItemDecoration.Builder(getContext());
        builder.dividerHorSize = 4;
        builder.dividerVerSize = 4;
        GridItemDecoration gridItemDecoration = new GridItemDecoration(builder);
        //防止重复设置addItemDecoration造成item间距变大
        if (recyclerView.getItemDecorationCount() == 0) {
            recyclerView.addItemDecoration(gridItemDecoration);
        }

        TypeOneAdapter typeOneAdapter = new TypeOneAdapter(getContext(), datas);
        if (datas.size() == 1) {
            placeholderView.setVisibility(GONE);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        } else if (datas.size() == 2 || datas.size() == 4) {
            placeholderView.setVisibility(VISIBLE);
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        } else if (datas.size() <= 9) {
            placeholderView.setVisibility(GONE);
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        }

        recyclerView.setAdapter(typeOneAdapter);
    }
}
