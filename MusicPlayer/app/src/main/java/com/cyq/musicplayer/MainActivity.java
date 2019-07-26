package com.cyq.musicplayer;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.more));
        ViewGroup contentFrameLayout = findViewById(android.R.id.content);
        View parentView = contentFrameLayout.getChildAt(0);
        if (parentView != null) {
            parentView.setFitsSystemWindows(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_music, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
