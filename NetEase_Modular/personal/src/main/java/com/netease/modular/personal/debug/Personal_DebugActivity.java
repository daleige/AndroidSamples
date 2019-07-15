package com.netease.modular.personal.debug;

import android.os.Bundle;
import android.util.Log;

import com.netease.common.base.BaseActivity;
import com.netease.common.utils.Cons;
import com.netease.modular.personal.R;

public class Personal_DebugActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_debug_activity);
        Log.e(Cons.TAG, "Personal/Personal_DebugActivity");

    }
}
