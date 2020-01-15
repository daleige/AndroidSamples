package com.cyq.customview.flowLayout;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.cyq.customview.R;

public class FlowLayoutActivity extends AppCompatActivity {

    private ImageView mIv1;
    private ImageView mIv2;
    private ImageView mIv3;
    private ImageView mIv4;
    private ImageView mIv5;
    private ImageView mIv6;
    private ImageView mIv7;
    private ImageView mIv8;
    private ImageView mIv9;

    private final String URL_IMG = "http://q3x62hkt1.bkt.clouddn.com/banner/58f57dfa5bb73.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_layout);
        initView();
    }

    private void initView() {
        mIv1 = (ImageView) findViewById(R.id.iv_1);
//        mIv2 = (ImageView) findViewById(R.id.iv_2);
//        mIv3 = (ImageView) findViewById(R.id.iv_3);
//        mIv4 = (ImageView) findViewById(R.id.iv_4);
//        mIv5 = (ImageView) findViewById(R.id.iv_5);
//        mIv6 = (ImageView) findViewById(R.id.iv_6);
//        mIv7 = (ImageView) findViewById(R.id.iv_7);
//        mIv8 = (ImageView) findViewById(R.id.iv_8);
//        mIv9 = (ImageView) findViewById(R.id.iv_9);

        Glide.with(this).load(URL_IMG).into(mIv1);

        Glide.with(this)
                .asBitmap()
                .load(URL_IMG)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                        int w = bitmap.getWidth();
                        int h = bitmap.getHeight();
                        Log.i("test", "图片宽高：" + w + "----" + h);
                        final LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(400,
                                400);//两个400分别为添加图片的大小
                        if (w >= h) {
                            lp.width = 100;
                            lp.height = 200;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mIv1.setLayoutParams(lp);
                                }
                            });
                        } else {

                        }
                    }
                });
//        Glide.with(this).load(URL_IMG).into(mIv2);
//        Glide.with(this).load(URL_IMG).into(mIv3);
//        Glide.with(this).load(URL_IMG).into(mIv4);
//        Glide.with(this).load(URL_IMG).into(mIv5);
//        Glide.with(this).load(URL_IMG).into(mIv6);
//        Glide.with(this).load(URL_IMG).into(mIv7);
//        Glide.with(this).load(URL_IMG).into(mIv8);
//        Glide.with(this).load(URL_IMG).into(mIv9);
    }


}
