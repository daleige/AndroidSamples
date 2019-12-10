package com.cyq.breadscaleview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.cyq.library.BreadScaleView;
import com.cyq.library.BreadScrollView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BreadScaleView scaleView = findViewById(R.id.bv_test);
        scaleView.setOnItemChangeLietener(new BreadScrollView.OnItemChangeListener() {
            @Override
            public void onItemChanged(int position) {

            }

            @Override
            public void onItemChange(int position, int oldPosition) {

            }
        });
    }
}
