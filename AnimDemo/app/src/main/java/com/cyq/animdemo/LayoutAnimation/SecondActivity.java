package com.cyq.animdemo.LayoutAnimation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;

import com.cyq.animdemo.R;

public class SecondActivity extends AppCompatActivity {
    private final int REQUEST_CODE = 101;
    private LinearLayout llContainer;
    private LayoutAnimationController openEnterController;
    private LayoutAnimationController closeExitController;
    private LayoutAnimationController openExitController;
    private LayoutAnimationController closeEnterController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        llContainer = findViewById(R.id.ll_container_2);
        openEnterController = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_open_exit);
        closeExitController = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_close_exit);
        openExitController = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_open_enter);
        closeEnterController = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_close_enter);

        llContainer.setLayoutAnimation(openEnterController);
    }

    public void back(View view) {
        finish();
        overridePendingTransition(R.anim.second_transition_close_enter, R.anim.second_transition_close_exit);
        llContainer.clearAnimation();
        llContainer.setLayoutAnimation(null);
        llContainer.setLayoutAnimation(closeExitController);
        llContainer.startLayoutAnimation();
    }

    public void next(View view) {
        startActivityForResult(new Intent(this, ThreeActivity.class), REQUEST_CODE);
        overridePendingTransition(R.anim.second_transition_open_enter, R.anim.second_transition_open_exit);
        llContainer.clearAnimation();
        llContainer.setLayoutAnimation(null);
        llContainer.setLayoutAnimation(openExitController);
        llContainer.startLayoutAnimation();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        assert data != null;
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            Log.e("test", "执行动画");
            llContainer.clearAnimation();
            llContainer.setLayoutAnimation(null);
            llContainer.setLayoutAnimation(closeEnterController);
            llContainer.startLayoutAnimation();
        }
    }
}
