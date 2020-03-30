package com.cyq.animdemo.transition;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import com.cyq.animdemo.R;

public class OneActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBack;
    private Button mNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setAllowEnterTransitionOverlap(true);
        getWindow().setAllowReturnTransitionOverlap(true);
        setContentView(R.layout.activity_one);
        setupWindowAnimations();

        mBack = findViewById(R.id.btn_back);
        mNext = findViewById(R.id.btn_next);
        mBack.setOnClickListener(this);
        mNext.setOnClickListener(this);
    }

    private void setupWindowAnimations() {
        Slide enter = new Slide(Gravity.RIGHT);
        enter.setDuration(2000);
        enter.setInterpolator(new LinearInterpolator());

        Slide exit = new Slide();
        exit.setDuration(2000);
        exit.setSlideEdge(Gravity.LEFT);
        exit.setInterpolator(new LinearInterpolator());

        Slide reenter = new Slide(Gravity.LEFT);
        reenter.setDuration(2000);
        reenter.setInterpolator(new LinearInterpolator());

        Slide returnT = new Slide(Gravity.RIGHT);
        returnT.setDuration(2000);
        returnT.setInterpolator(new LinearInterpolator());


        getWindow().setExitTransition(exit);
        getWindow().setReenterTransition(reenter);
        getWindow().setEnterTransition(enter);
        getWindow().setReturnTransition(returnT);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finishAfterTransition();
                break;
            case R.id.btn_next:
                Intent intent = new Intent(this, TowActivity.class);
                ActivityOptionsCompat transitionActivityOptions =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(this, null);
                startActivity(intent, transitionActivityOptions.toBundle());
                break;
        }
    }
}
