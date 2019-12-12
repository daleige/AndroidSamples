package com.cyq.graphchatview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private GraphChatView mGraphChatView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<TempBean> tempList = new ArrayList<>();
        TempBean tempBean1 = new TempBean();
        tempBean1.setTimestamp(1575993600);
        tempBean1.setTemp(20);
        TempBean tempBean2 = new TempBean();
        tempBean2.setTimestamp(1575994500); //15
        tempBean2.setTemp(90);
        TempBean tempBean3 = new TempBean();
        tempBean3.setTimestamp(1575994800); //20
        tempBean3.setTemp(170);
        TempBean tempBean4 = new TempBean();
        tempBean4.setTimestamp(1575994920); //22
        tempBean4.setTemp(218);
        TempBean tempBean5 = new TempBean();
        tempBean5.setTimestamp(1575995400);//30
        tempBean5.setTemp(260);
        TempBean tempBean6 = new TempBean();
        tempBean6.setTimestamp(1575995700);//35
        tempBean6.setTemp(260);
        TempBean tempBean7 = new TempBean();
        tempBean7.setTimestamp(1575995880);//38
        tempBean7.setTemp(260);
        TempBean tempBean8 = new TempBean();
        tempBean8.setTimestamp(1575996180);//43
        tempBean8.setTemp(260);
        tempList.add(tempBean1);
        tempList.add(tempBean2);
        tempList.add(tempBean3);
        tempList.add(tempBean4);
        tempList.add(tempBean5);
        tempList.add(tempBean6);
        tempList.add(tempBean7);
        tempList.add(tempBean8);

        mGraphChatView = findViewById(R.id.graph_view);
        mGraphChatView.setYAxis(60, "0", "60", "120", "180", "240", "300");
        mGraphChatView.setTempList(tempList);
    }
}
