package com.cyq.graphchatview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private GraphChatView mGraphChatView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<TempBean> tempList = new ArrayList<>();
        Random random = new Random();
        int temp = 0;
        for (int i = 0; i < 7; i++) {
            TempBean tempBean = new TempBean();
            tempBean.setTimestamp(System.currentTimeMillis() / 1000 + 300 * i);
            temp += (random.nextInt(50)+20);
            if (temp < 300) {
                tempBean.setTemp(temp);
                tempList.add(tempBean);
            }
        }

        mGraphChatView = findViewById(R.id.graph_view);
//        mGraphChatView.setYAxis(60, "0", "60", "120", "180", "240", "300");
        mGraphChatView.setTempList(tempList);
    }
}
