package com.cyq.graphchatview;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

        parseJson();
    }

    private List<TempBean> mTestData = new ArrayList<>();

    /**
     * 解析本地Json
     */
    private void parseJson() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    InputStream inputstream = getResources().getAssets().open("temperature.json");
                    StringBuilder data = new StringBuilder();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputstream));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        data.append(line);
                    }
                    String reponData = data.toString();
                    Gson gson = new Gson();
                    TestTempBean appList = gson.fromJson(reponData, TestTempBean.class);
                    Log.i("test", "解析结果：" + appList.getData().size());



                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void refreshData(View view) {
        tempList.clear();
        int temp = 10;
        for (int i = 0; i < 40; i++) {
            TempBean tempBean = new TempBean();
            //测试5秒递进一下
            tempBean.setTimestamp(System.currentTimeMillis() / 1000 + 5 * i);
            temp = temp + random.nextInt(10);
            tempBean.setTemp(temp);
            tempList.add(tempBean);
        }
        mGraphChatView.setTempList(tempList);
    }
}
