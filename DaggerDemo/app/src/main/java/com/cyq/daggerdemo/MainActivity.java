package com.cyq.daggerdemo;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    @Inject()
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //把自己(MainActivity--this)交给Dagger2
        DaggerStudentComponent.create().injectMainActivity(this);

        Log.i("test", student.name);
    }
}
