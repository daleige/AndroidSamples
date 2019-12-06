package com.cyq.statelayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cyq.lib_statelayout.view.StateLayout;

/**
 * @author ChenYangQi
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private StateLayout mStateLayout;
    private Button mBtnShowError;
    private Button mBtnShowLoading;
    private Button mBtnShowContent;
    private Button mBtnShowCustom;
    private Button mBtnShowEmpty;

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
        mBtnShowCustom = findViewById(R.id.btn_show_custom);
        mBtnShowEmpty = findViewById(R.id.btn_show_empty);
        mBtnShowError.setOnClickListener(this);
        mBtnShowLoading.setOnClickListener(this);
        mBtnShowContent.setOnClickListener(this);
        mBtnShowCustom.setOnClickListener(this);
        mBtnShowEmpty.setOnClickListener(this);

        mStateLayout.setRetryClickListener(new StateLayout.OnRetryClickListener() {
            @Override
            public void onClick() {
                Toast.makeText(MainActivity.this, "点击重试......", Toast.LENGTH_SHORT).show();
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
            case R.id.btn_show_empty:
                mStateLayout.showEmpty();
                break;
            case R.id.btn_show_content:
                mStateLayout.showContent();
                break;
            case R.id.btn_show_custom:
                FrameLayout customStateView = (FrameLayout) getLayoutInflater().inflate(R.layout.custom_layout, null, false);
                mStateLayout.showCustomView(customStateView);
                break;
            default:
        }
    }
}
