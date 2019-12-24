package com.cyq.greendaodemo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button btnInsert, btnQuery;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnInsert = findViewById(R.id.btn_insert);
        btnQuery = findViewById(R.id.btn_query);
        tvResult = findViewById(R.id.tv_result);
    }
}
