package com.cyq.animdemo.transition;

import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.cyq.animdemo.R;

public class TowActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBack;
    private Button mNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setAllowEnterTransitionOverlap(true);
        getWindow().setAllowReturnTransitionOverlap(true);
        setupWindowAnimations();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tow);

        mBack = findViewById(R.id.btn_back);
        mNext = findViewById(R.id.btn_next);

        mBack.setOnClickListener(this);
        mNext.setOnClickListener(this);
    }

    private void setupWindowAnimations() {
        Slide enterTransition = new Slide();
        enterTransition.setDuration(1000);
        enterTransition.setInterpolator(new DecelerateInterpolator());
        enterTransition.setSlideEdge(Gravity.RIGHT);
        getWindow().setEnterTransition(enterTransition);
        getWindow().setReturnTransition(enterTransition);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finishAfterTransition();
                break;
            case R.id.btn_next:

                break;
        }
    }

}
