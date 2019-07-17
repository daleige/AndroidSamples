package com.netease.modular.order;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.netease.common.base.BaseActivity;
import com.netease.common.utils.Cons;

public class Order_MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity_main);
        Log.e(Cons.TAG, "Order/Order_MainActivity");
    }

    public void jumpApp(View view) {
        try {
            //类加载方式交互
            Class targetClass = Class.forName("com.netease.modular.MainActivity");
            Intent intent = new Intent(this, targetClass);
            intent.putExtra("name", "simon");
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void jumpPersonal(View view) {
        try {
            //类加载方式交互
            Class targetClass = Class.forName("com.netease.modular.personal.Personal_MainActivity");
            Intent intent = new Intent(this, targetClass);
            intent.putExtra("name", "simon");
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
