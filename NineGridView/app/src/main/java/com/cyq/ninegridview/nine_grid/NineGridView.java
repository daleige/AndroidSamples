package com.cyq.ninegridview.nine_grid;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
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
    private List<String> datas;

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
    }

    /**
     * 设置数据，初始化Recyclerview
     *
     * @param datas
     */
    public void setData(List<String> datas) {
        this.datas = datas;
        if (datas.size() == 1) {
            TypeOneAdapter typeOneAdapter = new TypeOneAdapter(getContext(), datas.get(0));
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(typeOneAdapter);
        } else if (datas.size() <= 4) {


        } else if (datas.size() <= 9) {

        }
    }
}
