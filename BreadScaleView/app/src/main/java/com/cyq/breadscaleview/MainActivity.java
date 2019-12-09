package com.cyq.breadscaleview;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<String> items = new ArrayList<>();
        items.add("张三");
        items.add("张三");
        items.add("张三");
        items.add("张三");
        items.add("张三");
        items.add("张三");
        items.add("张三");
        items.add("张三");
        items.add("张三");
        WheelView wheelView = findViewById(R.id.wh);
        wheelView.setItems(items);

        wheelView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                super.onSelected(selectedIndex, item);

            }
        });

        //-------------------------------------------------------------------------
        com.cyq.breadscaleview.view.WheelView wheelView1 = findViewById(R.id.wv_test);
        wheelView1.setData(items);
        wheelView1.setOnSelectListener(new com.cyq.breadscaleview.view.WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                // Log.i("test", "-----------id:" + id + "-------------text:" + text);
            }

            @Override
            public void selecting(int id, String text) {
                //Log.i("id", "************id:" + id + "********text:" + text);
            }
        });
    }
}
