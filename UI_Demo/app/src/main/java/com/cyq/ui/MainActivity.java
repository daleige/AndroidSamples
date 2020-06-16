package com.cyq.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.cyq.ui.dialog.DialogListActivity;
import com.cyq.ui.dialog.constraintlayout.TestConstraintActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnDialog;
    private Button mBtnBanner;
    private Button mBtnConstraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mBtnDialog = findViewById(R.id.btn_dialog);
        mBtnBanner = findViewById(R.id.btn_banner);
        mBtnConstraintLayout = findViewById(R.id.btnConstraintLayout);
        mBtnDialog.setOnClickListener(this);
        mBtnBanner.setOnClickListener(this);
        mBtnConstraintLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dialog:
                Intent intent = new Intent(this, DialogListActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_banner:

                break;
            case R.id.btnConstraintLayout:
                Intent btnConstraintLayoutIntent = new Intent(this, TestConstraintActivity.class);
                startActivity(btnConstraintLayoutIntent);
                break;
            default:
        }
    }
}
