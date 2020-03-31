package com.cyq.animdemo.transition;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.cyq.animdemo.R;
import com.cyq.animdemo.transition.utils.EnterSlide;
import com.cyq.animdemo.transition.utils.ExitSlide;
import com.cyq.animdemo.transition.utils.ReenterSlide;
import com.cyq.animdemo.transition.utils.ReturnSlide;

public class TowActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBack;
    private Button mNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tow);
        setupWindowAnimations();
        mBack = findViewById(R.id.btn_back);
        mNext = findViewById(R.id.btn_next);

        mBack.setOnClickListener(this);
        mNext.setOnClickListener(this);
    }

    private void setupWindowAnimations() {
        EnterSlide enter = new EnterSlide();
        ExitSlide exit = new ExitSlide();
        ReenterSlide reenter = new ReenterSlide();
        ReturnSlide returnT = new ReturnSlide();

        getWindow().setReturnTransition(returnT);
        getWindow().setExitTransition(exit);
        getWindow().setEnterTransition(enter);
        getWindow().setReenterTransition(reenter);
//        getWindow().setAllowEnterTransitionOverlap(true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finishAfterTransition();
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
