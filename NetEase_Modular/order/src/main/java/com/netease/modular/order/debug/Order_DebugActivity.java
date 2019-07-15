package com.netease.modular.order.debug;

import android.os.Bundle;
import android.util.Log;

import com.netease.common.base.BaseActivity;
import com.netease.common.utils.Cons;
import com.netease.modular.order.R;

public class Order_DebugActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_debug_activit);
        Log.e(Cons.TAG, "Order/Order_DebugActivity");
    }
}
