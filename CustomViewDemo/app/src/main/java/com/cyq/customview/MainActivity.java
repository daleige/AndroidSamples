package com.cyq.customview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.cyq.customview.drawText.DrawTextActivity;
import com.cyq.customview.flowLayout.FlowLayoutActivity;
import com.cyq.customview.paintView.PaintViewActivity;
import com.cyq.customview.shadowLayout.ShadowLayoutActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnShadowLayout;
    private Button mBtnDrawText;
    private Button mBtnPaintView;
    private Button mBtnFlowLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mBtnShadowLayout = findViewById(R.id.btn_shadow_layout);
        mBtnShadowLayout.setOnClickListener(this);
        mBtnDrawText = findViewById(R.id.btn_draw_text);
        mBtnDrawText.setOnClickListener(this);
        mBtnPaintView = (Button) findViewById(R.id.btn_paint_view);
        mBtnPaintView.setOnClickListener(this);
        mBtnFlowLayout = (Button) findViewById(R.id.btn_flow_layout);
        mBtnFlowLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn_shadow_layout:
                intent.setClass(this, ShadowLayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_draw_text:
                intent.setClass(this, DrawTextActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_paint_view:
                intent.setClass(this, PaintViewActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_flow_layout:
                intent.setClass(this, FlowLayoutActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
