package com.cyq.animademo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

/**
 * @author chenyangqi
 */
public class MainActivity extends AppCompatActivity {
    CommonButtonView mCommonBtnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCommonBtnView = findViewById(R.id.common_btn_view);

        String[] items = {"高亮按钮", "普通按钮1", "普通按钮2", "普通按钮3"};
        mCommonBtnView.create(ButtonType.BLUE, items, true,
                new CommonButtonView.CommonButtonViewAction() {
                    @Override
                    public void onClick(int position) {
                        Toast.makeText(MainActivity.this, "点击了按钮" + position, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
