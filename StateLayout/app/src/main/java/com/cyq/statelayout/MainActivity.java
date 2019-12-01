package com.cyq.statelayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cyq.lib_statelayout.interfaces.IErrorState;
import com.cyq.lib_statelayout.view.StateLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private StateLayout mStateLayout;
    private Button mBtnShowError;
    private Button mBtnShowLoading;
    private Button mBtnShowContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mStateLayout = findViewById(R.id.state_layout);
        mBtnShowError = findViewById(R.id.btn_show_error);
        mBtnShowLoading = findViewById(R.id.btn_show_loading);
        mBtnShowContent = findViewById(R.id.btn_show_content);
        mBtnShowError.setOnClickListener(this);
        mBtnShowLoading.setOnClickListener(this);
        mBtnShowContent.setOnClickListener(this);

        //设置点击重试
        mStateLayout.setOnRetryClickListener(new IErrorState.OnRetryClickListener() {
            @Override
            public void onRetryClicked() {
                Toast.makeText(MainActivity.this, "点击重试", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_show_loading:
                mStateLayout.showLoading();
                break;
            case R.id.btn_show_error:
                mStateLayout.showError();
                break;
            case R.id.btn_show_content:
                mStateLayout.showContent();
                break;
            default:
        }
    }
}
