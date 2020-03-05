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
    private LayoutAnimationController inController;
    private LayoutAnimationController backController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        llContainer = findViewById(R.id.ll_container_2);
        inController = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_open_exit);
        backController = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_close_exit);
    }

    @Override
    protected void onResume() {
        super.onResume();
        llContainer.setLayoutAnimation(inController);
    }

    public void back(View view) {
        finish();
        overridePendingTransition(R.anim.second_transition_close_enter, R.anim.second_transition_close_exit);
//        for (int i = 0; i < llContainer.getChildCount(); i++) {
//            llContainer.getChildAt(i).clearAnimation();
//        }
        llContainer.clearAnimation();
        llContainer.setLayoutAnimation(null);
        llContainer.setLayoutAnimation(backController);
        llContainer.startLayoutAnimation();
    }
}
