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
    private LayoutAnimationController layoutController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        llContainer = findViewById(R.id.ll_container_2);
        layoutController = AnimationUtils.loadLayoutAnimation(this, R.anim.right_to_left_layout_animation);
        llContainer.setLayoutAnimation(layoutController);
        llContainer.startLayoutAnimation();
    }

    public void back(View view) {
        finish();
        overridePendingTransition(R.anim.second_transition_close_enter, R.anim.second_transition_close_exit);
    }
}
