package com.cyq.ninegridview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<List<String>> dates;
    private final String img = "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u" +
            "=3928028284,773541350&fm=26&gp=0.jpg";
    private IndexAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerview);
        dates = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            List<String> paths = new ArrayList<>();
            for (int j = 0; j <= i % 9; j++) {
                paths.add(img);
            }
            dates.add(paths);
        }
        mAdapter = new IndexAdapter(this, dates);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);
    }
}
