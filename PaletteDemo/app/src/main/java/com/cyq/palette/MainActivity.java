package com.cyq.palette;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.palette.graphics.Palette;
import androidx.palette.graphics.Target;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private int COLOR_DEFAULT = Color.WHITE;
    private ViewPager mViewPager;
    private FrameLayout mFlContainer;
    private List<Integer> mDate = new ArrayList<>();
    private List<ImageView> views = new ArrayList<>();
    private PagerAdapter mAdapter;
    private List<Integer> colors = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDate.add(R.drawable.pig111);
        mDate.add(R.drawable.pig222);
        mDate.add(R.drawable.pig333);
        mDate.add(R.drawable.pig444);
        mDate.add(R.drawable.pig555);
        mDate.add(R.drawable.pig666);
        mDate.add(R.drawable.pig777);
        mDate.add(R.drawable.pig888);
        mDate.add(R.drawable.pig999);
        mDate.add(R.drawable.pig10);

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
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.i("test", "position:" + position);
                fetchColor(mDate.get(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 计算颜色
     *
     * @param resourceId
     */
    private void fetchColor(int resourceId) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resourceId);
        Palette.Builder paletteBuild = Palette.from(bitmap);
        paletteBuild.generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(@Nullable Palette palette) {
                Palette.Swatch dominantSwatch = palette.getSwatchForTarget(Target.VIBRANT);
                int color = dominantSwatch != null ? dominantSwatch.getRgb() : COLOR_DEFAULT;
                Log.i("test", "颜色：" + color);
                mFlContainer.setBackgroundColor(color);
            }
        });
    }
}
