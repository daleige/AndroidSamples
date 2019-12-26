package com.cyq.jdtoolbardemo;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FrameLayout flContainer;
    private RecyclerView mRecyclerview;
    private List<String> mList = new ArrayList<>();
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flContainer = findViewById(R.id.fl_container);
        mRecyclerview = findViewById(R.id.recyclerview);

        for (int i = 0; i < 20; i++) {
            mList.add("");
        }

        myAdapter = new MyAdapter(R.layout.item_layout_list, mList);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setAdapter(myAdapter);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mRecyclerview.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                    Log.i("test", "i:" + i + " i1:" + i1 + " i2:" + i2 + " i3:" + i3);
                }
            });

            mRecyclerview.setOnFlingListener(new RecyclerView.OnFlingListener() {
                @Override
                public boolean onFling(int velocityX, int velocityY) {
                    Log.i("test","velocityX:"+velocityX+ "velocityY:"+velocityY);
                    return false;
                }

            });
        }
    }
}
