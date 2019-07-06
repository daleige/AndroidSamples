package com.cyq.ninegridview.nine_grid;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Create by 陈扬齐
 * Create on 2019-07-07
 * description:
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
//        if (parent.getChildAdapterPosition(view) % 3 == 0) {

        outRect.left = 0;
        outRect.top = 0;
        outRect.right = 0;
        outRect.bottom = 0;
    }
}
