package com.cyq.graphchatview;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private GraphChatView mGraphChatView;
    private List<TempBean> tempList = new ArrayList<>();
    private Random random;
    private SeekBar mSeekbar;

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
        mSeekbar = findViewById(R.id.sb_test);
        mGraphChatView.setTempList(tempList);

        mSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.i("test","onProgressChanged:"+i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.i("test","onStartTrackingTouch");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.i("test","onStopTrackingTouch");
            }
        });

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
                    Gson gson = new Gson();
                    TestTempBean appList = gson.fromJson(data.toString(), TestTempBean.class);
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    mTestData.clear();
                    for (TestTempBean.DataBean bean : appList.getData()) {
                        Date date = format.parse(bean.getAddTime());
                        TempBean mTempBean = new TempBean();
                        mTempBean.setTimestamp(date.getTime() / 1000);
                        mTempBean.setTemp(Integer.parseInt(bean.getActualTemperature()));
                        mTestData.add(mTempBean);
                    }
                    if (mHandler != null) {
                        mHandler.sendEmptyMessage(0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            mGraphChatView.setTempList(mTestData);
            return false;
        }
    });
}
