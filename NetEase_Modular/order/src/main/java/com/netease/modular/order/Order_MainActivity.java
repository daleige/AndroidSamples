package com.netease.modular.order;

import android.os.Bundle;
import android.util.Log;

import com.netease.common.base.BaseActivity;
import com.netease.common.utils.Cons;

public class Order_MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity_main);
        Log.e(Cons.TAG, "Order/Order_MainActivity");
    }
}
