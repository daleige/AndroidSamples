package com.cyq.palette;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

/**
 * @author : ChenYangQi
 * date   : 2020/1/19 16:57
 * desc   :
 */
public class MyPageAdapter extends PagerAdapter {
    private Context mContext;
    private List<ImageView> views;

    public MyPageAdapter(Context mContext, List<ImageView> views) {
        this.mContext = mContext;
        this.views = views;
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public ImageView instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = views.get(position);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ImageView imageView = (ImageView) object;
        container.removeView(imageView);
    }
}
