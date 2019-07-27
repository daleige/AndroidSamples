package com.wangyi.musicplayer;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.PersistableBundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.wangyi.musicplayer.ui.UIUtils;
import com.wangyi.musicplayer.ui.ViewCalculateUtil;
import com.wangyi.musicplayer.util.StatusBarUtil;
import com.wangyi.musicplayer.view.MyNestedScrollView;

import java.lang.reflect.Method;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class MainActivity extends AppCompatActivity {
    public final static String IMAGE_URL_MEDIUM = "https://p2.music.126.net/paUj45NZvs1jWAOzgmMyfg==/109951164210594807.webp?imageView&thumbnail=720x720";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UIUtils.getInstance(this);

        initView();
        notifyData();
        postImage();
        initScrollViewListener();
    }


    int slidingDistance;
    private void initScrollViewListener() {
//        最好的 测量 就是不测量    view.getParams().height
        slidingDistance = UIUtils.getInstance().getHeight(490);
        myNestedScrollView.setOnMyScrollChangeListener(new MyNestedScrollView.ScrollInterface() {


            @Override
            public void onScrollChange(int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                scrollChangeHeader(scrollY);
            }


        });

    }

    private void scrollChangeHeader(int scrolledY) {
        if (scrolledY < 0) {
            scrolledY = 0;
        }
//0  1
        float alpha = Math.abs(scrolledY) * 1.0f / (slidingDistance);
        Drawable drawable = toolbar_bg.getDrawable();
        if (drawable != null) {
            //490
            if (scrolledY <= slidingDistance) {
                drawable.mutate().setAlpha((int) (alpha * 255));
                toolbar_bg.setImageDrawable(drawable);
            }else {
                //490
                drawable.mutate().setAlpha(255);
                toolbar_bg.setImageDrawable(drawable);
            }
        }


    }
    private void postImage() {
        Glide.with(this)
                .load(IMAGE_URL_MEDIUM)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        Log.i("tuch", "onException: "+e);
                        return false;
                    }
                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        Log.i("tuch", "onResourceReady: ");
                        return false;
                    }
                }).override(400, 400)
                .into(header_image_item);

        // "14":模糊度；"3":图片缩放3倍后再进行模糊
        Glide.with(this)
                .load(IMAGE_URL_MEDIUM)
                .error(R.drawable.stackblur_default)
                .placeholder(R.drawable.stackblur_default)
                .crossFade(500)
                .bitmapTransform(new BlurTransformation(this, 200, 3))
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        lv_header_contail.setBackground(resource);
                    }
                });
        Glide.with(this).load(IMAGE_URL_MEDIUM)
                .error(R.drawable.stackblur_default)
                .bitmapTransform(new BlurTransformation(this, 250, 6))// 设置高斯模糊
                .listener(new RequestListener<String, GlideDrawable>() {//监听加载状态
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }
                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        toolbar.setBackgroundColor(Color.TRANSPARENT);
                        toolbar_bg.setImageAlpha(0);
                        toolbar_bg.setVisibility(View.VISIBLE);
                        return false;
                    }
                }).into(toolbar_bg);
    }

    private void notifyData() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        music_recycler.setLayoutManager(mLayoutManager);
        // 需加，不然滑动不流畅
        music_recycler.setNestedScrollingEnabled(false);
        music_recycler.setHasFixedSize(false);
        final MusicAdapter adapter = new MusicAdapter(this);
        adapter.notifyDataSetChanged();
        music_recycler.setAdapter(adapter);
    }

    //
    Toolbar toolbar;
    ImageView toolbar_bg;
    ImageView header_bg;
    RecyclerView music_recycler;
    LinearLayout lv_header_contail;
    ImageView header_music_log;
    ImageView header_image_item;
    MyNestedScrollView myNestedScrollView;
    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.more));


        toolbar_bg = findViewById(R.id.toolbar_bg);
        header_bg = findViewById(R.id.header_bg);
        music_recycler = findViewById(R.id.music_recycler);
        myNestedScrollView = findViewById(R.id.nsv_scrollview);
        header_music_log = findViewById(R.id.header_music_log);
        LinearLayout lv_header_detail = findViewById(R.id.lv_header_detail);
        RelativeLayout rv_header_container = findViewById(R.id.rv_header_container);
        lv_header_contail = findViewById(R.id.lv_header_contail);
        header_image_item = findViewById(R.id.header_image_item);

        ViewCalculateUtil.setViewLayoutParam(toolbar, 1080, 164, 0, 0, 0, 0);
        ViewCalculateUtil.setViewLinearLayoutParam(rv_header_container,1080,770,164,0,0,0);
        ViewCalculateUtil.setViewLayoutParam(toolbar,1080, 164, 0, 0, 0, 0);
        ViewCalculateUtil.setViewLayoutParam(toolbar_bg,1080,164+UIUtils.getInstance().getSystemBarHeight(this),0,0,0,0);
        ViewCalculateUtil.setViewLayoutParam(header_bg, 1080, 850, 0, 0, 0, 0);
        ViewCalculateUtil.setViewLayoutParam(lv_header_detail, 1080, 380, 72, 0, 52, 0);
        ViewCalculateUtil.setViewLinearLayoutParam(header_image_item,380,380);
        ViewCalculateUtil.setViewLayoutParam(header_music_log,60,60,59,0,52,0);

        StatusBarUtil.setStateBar(this, toolbar);



    }

        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_music, menu);
        return true;
    }

}
