package com.cyq.customview.flowLayout;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.cyq.customview.R;
import com.cyq.customview.nineLayout.NineImageLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlowLayoutActivity extends AppCompatActivity {
    final List<String> strList = new ArrayList<>(Arrays.asList("Stack Overflow is an open community for anyone that codes. We help you get answers to your toughest coding questions, share knowledge with your coworkers in private, and find your next dream job".split(" ")));
    private ImageView mIv1;
    private ImageView mIv2;
    private ImageView mIv3;
    private ImageView mIv4;
    private ImageView mIv5;
    private ImageView mIv6;
    private ImageView mIv7;
    private ImageView mIv8;
    private ImageView mIv9;
    private NineImageLayout nineImageLayout;
    private TabFlowLayout tabFlowLayout;

    //private final String URL_IMG = "http://q3x62hkt1.bkt.clouddn.com/banner/58f57dfa5bb73.jpg";
    private final String URL_IMG = "http://q3x62hkt1.bkt.clouddn.com/timg.jpeg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_layout);
        initView();
        initTabFlowLayout();
    }

    private void initTabFlowLayout() {
        tabFlowLayout = findViewById(R.id.tab_fl);
        tabFlowLayout.setAdapter(new TagAdapter() {
            @Override
            int getItemCount() {
                return strList.size();
            }

            @Override
            View createView(LayoutInflater inflater, ViewGroup parent, int position) {
                return inflater.inflate(R.layout.item_tag_layout, parent, false);
            }

            @Override
            void bindView(View view, int position) {
                TextView textView = view.findViewById(R.id.tv);
                textView.setText(strList.get(position));
            }
        });
    }

    private void initView() {
        nineImageLayout = findViewById(R.id.nine_img_view);
        mIv1 = (ImageView) findViewById(R.id.iv_1);
        mIv2 = (ImageView) findViewById(R.id.iv_2);
        mIv3 = (ImageView) findViewById(R.id.iv_3);
        mIv4 = (ImageView) findViewById(R.id.iv_4);
        mIv5 = (ImageView) findViewById(R.id.iv_5);
        mIv6 = (ImageView) findViewById(R.id.iv_6);
        mIv7 = (ImageView) findViewById(R.id.iv_7);
        mIv8 = (ImageView) findViewById(R.id.iv_8);
        mIv9 = (ImageView) findViewById(R.id.iv_9);
        Glide.with(this).load(URL_IMG).into(mIv1);
        Glide.with(this)
                .asBitmap()
                .load(URL_IMG)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap bitmap,
                                                Transition<? super Bitmap> transition) {
                        int w = bitmap.getWidth();
                        int h = bitmap.getHeight();
                        Log.i("test", "图片宽高：" + w + "----" + h);
                        nineImageLayout.setSingleImage(w, h);
                    }
                });
        Glide.with(this).load(URL_IMG).into(mIv2);
        Glide.with(this).load(URL_IMG).into(mIv3);
        Glide.with(this).load(URL_IMG).into(mIv4);
        Glide.with(this).load(URL_IMG).into(mIv5);
        Glide.with(this).load(URL_IMG).into(mIv6);
        Glide.with(this).load(URL_IMG).into(mIv7);
        Glide.with(this).load(URL_IMG).into(mIv8);
        Glide.with(this).load(URL_IMG).into(mIv9);
    }
}
