package com.netease.modular;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.netease.common.utils.Cons;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(Cons.TAG, "App/MainActivity");
    }

    public void jumpOrder(View view) {

    }

    public void jumpPersonal(View view) {

    }
}
