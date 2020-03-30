package com.cyq.animdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;

import com.cyq.animdemo.LayoutAnimation.MainActivity;
import com.cyq.animdemo.transition.OneActivity;
import com.cyq.animdemo.transition.utils.TransitionHelper;

public class IndexActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mLayoutAnimation;
    private Button mTransition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        mLayoutAnimation = findViewById(R.id.btn_layout_animation);
        mTransition = findViewById(R.id.btn_transition);
        mLayoutAnimation.setOnClickListener(this);
        mTransition.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_layout_animation:
                Intent intent1 = new Intent(this, MainActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_transition:
                Intent inten2 = new Intent(IndexActivity.this, OneActivity.class);
                final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(IndexActivity.this, false);
                ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(IndexActivity.this, pairs);
                startActivity(inten2, transitionActivityOptions.toBundle());
                break;
            default:

        }
    }
}
