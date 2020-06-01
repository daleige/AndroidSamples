package com.cyq.customview.intercept;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cyq.customview.R;

public class InterceptActivity extends AppCompatActivity {
    private LinearLayout mLLContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intercept);
        mLLContainer = findViewById(R.id.llContainer);
        for (int i = 0; i < mLLContainer.getChildCount(); i++) {
            View view = mLLContainer.getChildAt(i);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(InterceptActivity.this, "点击------", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
