package com.netease.modular.personal;

import android.os.Bundle;
import android.util.Log;

import com.netease.common.base.BaseActivity;
import com.netease.common.utils.Cons;

public class Personal_MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_activity_main);
        Log.e(Cons.TAG, "Personal/Personal_MainActivity");
    }
}
