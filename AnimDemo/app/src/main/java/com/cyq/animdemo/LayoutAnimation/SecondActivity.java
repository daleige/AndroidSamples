package com.cyq.animdemo.LayoutAnimation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;

import com.cyq.animdemo.R;

public class SecondActivity extends AppCompatActivity {

    private LinearLayout llContainer;
    private LayoutAnimationController layoutControllerIn;

    private LayoutAnimationController layoutContrillerBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        llContainer = findViewById(R.id.ll_container_2);
        layoutControllerIn = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_open_exit);
        llContainer.setLayoutAnimation(layoutControllerIn);
        llContainer.startLayoutAnimation();


    }

    public void back(View view) {
        finish();
        overridePendingTransition(R.anim.second_transition_close_enter, R.anim.second_transition_close_exit);
    }
}
