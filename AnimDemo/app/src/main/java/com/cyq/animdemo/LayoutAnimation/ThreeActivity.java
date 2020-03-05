package com.cyq.animdemo.LayoutAnimation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.cyq.animdemo.R;

public class ThreeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void back(View view) {
        Intent intent = new Intent();
        intent.putExtra("LayoutAnimation", 102);
        setResult(RESULT_OK, intent);
        finish();
        overridePendingTransition(R.anim.second_transition_close_enter, R.anim.second_transition_close_exit);
    }
}
