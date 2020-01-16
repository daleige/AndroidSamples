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
import com.cyq.customview.nineLayout.NineImageAdapter;
import com.cyq.customview.nineLayout.NineImageLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlowLayoutActivity extends AppCompatActivity {
    final List<String> strList = new ArrayList<>(Arrays.asList("Stack Overflow is an open community for anyone that codes. We help you get answers to your toughest coding questions, share knowledge with your coworkers in private, and find your next dream job".split(" ")));
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

        final List<String> imgList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            imgList.add(URL_IMG);
        }
        nineImageLayout.setAdapter(new NineImageAdapter() {
            @Override
            protected int getItemCount() {
                return imgList.size();
            }

            @Override
            protected View createView(LayoutInflater inflater, ViewGroup parent, int position) {
                return inflater.inflate(R.layout.item_img_layout, parent, false);
            }

            @Override
            protected void bindView(View view, int position) {
                ImageView imageView = view.findViewById(R.id.iv_img);
                if (imgList.size() == 1) {
                    Glide.with(FlowLayoutActivity.this)
                            .asBitmap()
                            .load(URL_IMG)
                            .into(new SimpleTarget<Bitmap>() {
                                @Override
                                public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                                    final int w = bitmap.getWidth();
                                    final int h = bitmap.getHeight();
                                    nineImageLayout.setSingleImage(w, h);
                                }
                            });
                }
                Glide.with(FlowLayoutActivity.this).load(imgList.get(position)).into(imageView);
            }
        });
    }
}
