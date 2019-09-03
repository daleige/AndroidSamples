package com.cyq.toucheventdemo;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String Log = "TouchEventDemo";
    private MyView myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myView = findViewById(R.id.my_view);
        myView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        android.util.Log.d(Log, "my_view:手指按下");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        android.util.Log.d(Log, "my_view:手指移动");
                        break;
                    case MotionEvent.ACTION_UP:
                        android.util.Log.d(Log, "my_view:手指抬起");
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        android.util.Log.d(Log, "MainActivity:dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        android.util.Log.d(Log, "MainActivity:onTouchEvent");
        return super.onTouchEvent(event);
    }
}
