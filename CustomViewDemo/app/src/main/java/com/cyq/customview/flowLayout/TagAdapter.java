package com.cyq.customview.flowLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author : ChenYangQi
 * date   : 2020/1/16 9:31
 * desc   :
 */
public abstract class TagAdapter {
    abstract int getItemCount();

    abstract View createView(LayoutInflater inflater, ViewGroup parent, int position);

    abstract void bindView(View view, int position);
}
