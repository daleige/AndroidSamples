package com.cyq.palette;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private FrameLayout mFlContainer;
    private List<Integer> mDate = new ArrayList<>();
    private List<ImageView> views = new ArrayList<>();
    private PagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDate.add(R.drawable.pig111);
        mDate.add(R.drawable.pig222);
        mDate.add(R.drawable.pig333);
        mDate.add(R.drawable.pig444);
        for (int resourceId : mDate) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(this).load(resourceId).into(imageView);
            views.add(imageView);
        }
        initView();
    }

    private void initView() {
        mViewPager = findViewById(R.id.view_pager);
        mFlContainer = findViewById(R.id.fl_container);
        mAdapter = new MyPageAdapter(this, views);
        mViewPager.setAdapter(mAdapter);
    }


}
