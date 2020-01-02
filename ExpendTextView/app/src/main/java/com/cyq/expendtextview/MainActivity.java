package com.cyq.expendtextview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cyq.expendtextview.adapter.MyAdapter;
import com.cyq.expendtextview.bean.TestBean;
import com.cyq.expendtextview.view.Utils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private List<TestBean> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerView);
        int width = getWindowManager().getDefaultDisplay().getWidth() - Utils.dp2px(this, 20);
        //构造假数据
        for (int i = 0; i < 30; i++) {
            TestBean bean = new TestBean();
            if (i % 3 == 0) {
                bean.setStr("测试一行文字的");
            } else if (i % 4 == 0) {
                bean.setStr("测试二行文字的，测试二行文字的，测试二试二行文字的，测试二行文字的");
            } else if (i % 5 == 0) {
                bean.setStr("测试三行文字的，测试三行文字的，测试三行文字的，测试三行文字的，测试三行文字的，测试三行文字的" +
                        "测试三行文字的测试三行文字的" +
                        "测试三行文字的测试三行文字的测试三行文字的测试三行文字的测试三行文字的测试三行文字的测试三行文字的");
            } else {
                bean.setStr(getResources().getString(R.string.text_content));

            }
            bean.isChecked = false;
            mList.add(bean);
        }
        mAdapter = new MyAdapter(this, mList, width);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }
}
