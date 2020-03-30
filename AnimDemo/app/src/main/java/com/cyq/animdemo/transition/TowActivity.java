package com.cyq.animdemo.transition;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.cyq.animdemo.R;

public class TowActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBack;
    private Button mNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tow);

        mBack = findViewById(R.id.btn_back);
        mNext = findViewById(R.id.btn_next);

        mBack.setOnClickListener(this);
        mNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:

                break;
            case R.id.btn_next:

                break;
        }
    }

}
