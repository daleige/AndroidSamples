package com.cyq.daggerdemo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.cyq.daggerdemo.demo.DaggerPersonComponent;
import com.cyq.daggerdemo.demo.Man;
import com.cyq.daggerdemo.demo.Person;
import com.cyq.daggerdemo.demo.PersonModule;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    @Inject
    Person mPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerPersonComponent.builder()
                .personModule(new PersonModule(new Man(),18))
                .build()
                .inject(this);

        mPerson.info();
    }
}
