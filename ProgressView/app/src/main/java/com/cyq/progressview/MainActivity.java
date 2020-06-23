package com.cyq.progressview;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.cyq.progressview.widget.MyProgressView;

public class MainActivity extends AppCompatActivity {
    private MyProgressView mProgress;
    private boolean tagger = true;
    private FrameLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContainer = findViewById(R.id.mContainer);
        mProgress = findViewById(R.id.mProgress);
        mContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tagger) {
                    mProgress.animate()
                            .translationY(-200)
                            .scaleX(0.85f)
                            .scaleY(0.85f)
                            .setDuration(200)
                            .withLayer()
                            .start();
                } else {
                    mProgress.animate()
                            .translationY(0)
                            .scaleX(1f)
                            .scaleY(1f)
                            .setDuration(200)
                            .withLayer()
                            .start();
                }
                tagger = !tagger;
            }
        });
    }

}
