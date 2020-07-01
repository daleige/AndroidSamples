package com.cyq.progressview;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.cyq.progressview.widget.ProgressLayout;

/**
 * @author : ChenYangQi
 * date   : 2020/7/1 10:06
 * desc   : 全局的按钮控件，以下三类按钮的功能集合
 */
public class MainActivity extends AppCompatActivity {
    private ProgressLayout mProgress;
    private boolean tagger = true;
    private FrameLayout mContainer;
    private LinearLayout mBtnContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContainer = findViewById(R.id.mContainer);
        mProgress = findViewById(R.id.mProgress);
        mBtnContainer = findViewById(R.id.mBtnContainer);
        mContainer.setOnClickListener(v -> {
            if (tagger) {
                //按钮下移
                mBtnContainer.animate()
                        .translationY(500F)
                        .setDuration(200)
                        .withLayer()
                        .start();

                mBtnContainer.setVisibility(View.VISIBLE);
                mProgress.animate()
                        .translationY(0)
                        .setDuration(200)
                        .scaleX(1f)
                        .scaleY(1f)
                        .withLayer()
                        .start();
            } else {
                //按钮上移
                mBtnContainer.animate()
                        .translationY(0F)
                        .setDuration(200)
                        .withLayer()
                        .start();

                mProgress.animate()
                        .translationY(-250F)
                        .scaleX(0.8f)
                        .scaleY(0.8f)
                        .setDuration(200)
                        .withLayer()
                        .start();
            }
            tagger = !tagger;
        });
    }
}
