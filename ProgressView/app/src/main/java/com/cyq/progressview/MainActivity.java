package com.cyq.progressview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.cyq.progressview.widget.ThermometerView;

public class MainActivity extends AppCompatActivity {
    private ThermometerView mThermometerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mThermometerView=findViewById(R.id.mThermometerView);
    }
}
