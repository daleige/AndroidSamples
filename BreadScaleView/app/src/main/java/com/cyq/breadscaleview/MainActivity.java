package com.cyq.breadscaleview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.cyq.library.BreadScaleView;
import com.cyq.library.BreadScrollView;
import com.cyq.library.BreadType;
import com.cyq.library.ScaleBean;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //构造数据
        List<ScaleBean> mDatas = new ArrayList<>(9);
        for (int i = 0; i < 9; i++) {
            ScaleBean bean = new ScaleBean();
            bean.setScale(String.valueOf(i + 1));
            if (i == 0) {
                //下标位置0时，设置面包片图片1
                bean.setBreadType(BreadType.LIGHT);
            } else if (i == 4) {
                //下标位置4时，设置面包片图片2
                bean.setBreadType(BreadType.MIDDLE);
            } else if (i == 8) {
                //下标位置8时，设熟面包片图片3
                bean.setBreadType(BreadType.HEIGHT);
            } else {
                bean.setBreadType(BreadType.EMPTY);
            }
            mDatas.add(bean);
        }

        BreadScaleView scaleView = findViewById(R.id.bv_test);
        scaleView.setData(mDatas);
        scaleView.setOnItemChangeLietener(new BreadScrollView.OnItemChangeListener() {
            @Override
            public void onItemChanged(int position) {
            }

            @Override
            public void onItemChange(int position, int oldPosition) {

            }
        });

        //移动到下标为3的位置，也就是刻度中的4
        //scaleView.scrollTo(3);
    }
}
