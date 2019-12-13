package com.cyq.graphchatview;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private GraphChatView mGraphChatView;
    private List<TempBean> tempList = new ArrayList<>();
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        random = new Random();
        for (int i = 0; i < 9; i++) {
            TempBean tempBean = new TempBean();

            tempBean.setTimestamp(System.currentTimeMillis() / 1000 + 300 * i);
            tempBean.setTemp(random.nextInt(260));
            tempList.add(tempBean);
        }

        mGraphChatView = findViewById(R.id.graph_view);
        mGraphChatView.setTempList(tempList);

        /**
         * 设置Y轴坐标
         */
        //mGraphChatView.setYAxis(60, "0", "60", "120", "180", "240", "300");
    }

    public void refreshData(View view) {
        tempList.clear();
        int temp = 10;
        for (int i = 0; i < 40; i++) {
            TempBean tempBean = new TempBean();
            //测试5秒递进一下
            tempBean.setTimestamp(System.currentTimeMillis() / 1000 +5 * i);
            temp = temp + random.nextInt(10);
            tempBean.setTemp(temp);
            tempList.add(tempBean);
        }
        mGraphChatView.setTempList(tempList);
    }
}
