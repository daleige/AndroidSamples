package com.cyq.progressview;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.cyq.progressview.widget.ProgressLayout;

public class MainActivity extends AppCompatActivity {
    private ProgressLayout mProgress;
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


            }
        });
    }

}
