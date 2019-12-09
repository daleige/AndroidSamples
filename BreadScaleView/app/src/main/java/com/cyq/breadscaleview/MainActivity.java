package com.cyq.breadscaleview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<String> items=new ArrayList<>();
        items.add("张三");
        items.add("张三");
        items.add("张三");
        items.add("张三");
        items.add("张三");
        items.add("张三");
        items.add("张三");
        items.add("张三");
        items.add("张三");
        WheelView wheelView= findViewById(R.id.wh);
        wheelView.setItems(items);
    }
}
