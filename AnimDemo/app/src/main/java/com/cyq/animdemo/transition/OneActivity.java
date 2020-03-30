package com.cyq.animdemo.transition;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;

import com.cyq.animdemo.R;
import com.cyq.animdemo.transition.utils.TransitionHelper;

public class OneActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBack;
    private Button mNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setAllowEnterTransitionOverlap(true);
        getWindow().setAllowReturnTransitionOverlap(true);
        setupWindowAnimations();
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_one);

        mBack = findViewById(R.id.btn_back);
        mNext = findViewById(R.id.btn_next);
        mBack.setOnClickListener(this);
        mNext.setOnClickListener(this);
    }

    private void setupWindowAnimations() {
        Slide enter = new Slide();
        enter.setDuration(1000);
        enter.setSlideEdge(Gravity.RIGHT);
        enter.setInterpolator(new DecelerateInterpolator());

        Slide exit = new Slide();
        exit.setDuration(1000);
        exit.setSlideEdge(Gravity.LEFT);
        exit.setInterpolator(new DecelerateInterpolator());

        Slide reenter = new Slide();
        reenter.setSlideEdge(Gravity.LEFT);
        reenter.setDuration(1000);
        reenter.setInterpolator(new DecelerateInterpolator());

        Slide returnT = new Slide();
        returnT.setDuration(1000);
        returnT.setSlideEdge(Gravity.RIGHT);
        returnT.setInterpolator(new DecelerateInterpolator());

        getWindow().setReenterTransition(reenter);
        getWindow().setEnterTransition(enter);
        getWindow().setReturnTransition(returnT);
        getWindow().setExitTransition(exit);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finishAfterTransition();
                break;
            case R.id.btn_next:
                Intent i = new Intent(this, TowActivity.class);
                final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(OneActivity.this, false);
                ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(OneActivity.this, pairs);
                startActivity(i, transitionActivityOptions.toBundle());
                break;
        }
    }
}
