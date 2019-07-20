package com.netease.modular.apt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.netease.modular.annotation.ARouter;

@ARouter(path = "/app/PersonalActivity")
public class PersonalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
    }

    public void jump(View view) {
        Class<?> targetClass=MainActivity$$ARouter.findTargetClass("/app/MainActivity");
        startActivity(new Intent(this,targetClass));
    }
}
