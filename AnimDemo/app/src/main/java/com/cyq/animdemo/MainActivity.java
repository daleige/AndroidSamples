package com.cyq.animdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;

import com.cyq.animdemo.LayoutAnimation.SecondActivity;

public class MainActivity extends AppCompatActivity {
    private LayoutAnimationController inController;
    private LayoutAnimationController backController;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = findViewById(R.id.ll_container);
        inController = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_open_enter);
        backController = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_close_enter);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void next(View view) {
        startActivity(new Intent(this, SecondActivity.class));
        overridePendingTransition(R.anim.second_transition_open_enter, R.anim.second_transition_open_exit);
        linearLayout.setLayoutAnimation(inController);
        linearLayout.startLayoutAnimation();
    }


}
