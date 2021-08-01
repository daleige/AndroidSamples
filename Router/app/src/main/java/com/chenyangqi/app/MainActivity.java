package com.chenyangqi.app;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.chenyangqi.router.annotations.Destination;
import com.chenyangqi.router_runtime.Router;

@Destination(url = "router://home_page", description = "首页")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnGoLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Router.INSTANCE
                        .go(MainActivity.this, "router://page_login?username=zhangsan");
            }
        });
    }
}