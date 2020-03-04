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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout linearLayout = findViewById(R.id.ll_container);
        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(this, R.anim.left_to_right_layout_animation);
        linearLayout.setLayoutAnimation(controller);
    }

    public void next(View view) {
        startActivity(new Intent(this, SecondActivity.class));
    }
}
