package com.cyq.animdemo.transition;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.cyq.animdemo.R;

import static com.cyq.animdemo.transition.utils.Constant.DURATION;

public class TowActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBack;
    private Button mNext;
    private View rootView;

    private Animation exitAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tow);

        rootView = findViewById(R.id.root);
        if (savedInstanceState == null) {
            rootView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    rootView.getViewTreeObserver().removeOnPreDrawListener(this);
                    startRootAnimation();
                    return true;
                }
            });
        }

        exitAnimation = AnimationUtils.loadAnimation(this, R.anim.air_fry_transition_close_exit);

        //setupWindowAnimations();
        mBack = findViewById(R.id.btn_back);
        mNext = findViewById(R.id.btn_next);

        mBack.setOnClickListener(this);
        mNext.setOnClickListener(this);
    }

    private void startRootAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this,
                R.anim.air_fry_transition_open_enter);
        rootView.startAnimation(animation);
    }

    private void setupWindowAnimations() {
        Slide enter = new Slide();
        enter.setDuration(DURATION);
        enter.setSlideEdge(Gravity.RIGHT);
        enter.setInterpolator(new DecelerateInterpolator());

        Slide exit = new Slide();
        exit.setDuration(DURATION);
        exit.setSlideEdge(Gravity.LEFT);
        exit.setInterpolator(new DecelerateInterpolator());

        Slide reenter = new Slide();
        reenter.setSlideEdge(Gravity.LEFT);
        reenter.setDuration(DURATION);
        reenter.setInterpolator(new DecelerateInterpolator());

        Slide returnT = new Slide();
        returnT.setDuration(DURATION);
        returnT.setSlideEdge(Gravity.RIGHT);
        returnT.setInterpolator(new DecelerateInterpolator());

        ChangeBounds changeBounds = new ChangeBounds();
        changeBounds.setDuration(DURATION);
        changeBounds.setResizeClip(true);

        getWindow().setReenterTransition(reenter);
        getWindow().setEnterTransition(enter);
        getWindow().setReturnTransition(returnT);
        getWindow().setExitTransition(exit);
        getWindow().setAllowEnterTransitionOverlap(true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                setResult(RESULT_OK);
                finish();
                rootView.startAnimation(exitAnimation);
                break;
            case R.id.btn_next:

                break;
            default:
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
}
