package com.cyq.expendtextview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cyq.expendtextview.adapter.MyAdapter;
import com.cyq.expendtextview.adapter.TestAdapter;
import com.cyq.expendtextview.bean.DateBean;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private TestAdapter mAdapter;
    private List<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerView);
        //构造假数据
        for (int i = 0; i < 30; i++) {
            mList.add(getResources().getString(R.string.text_content));
        }
        mAdapter = new TestAdapter(R.layout.item_layout_list, mList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }
}
