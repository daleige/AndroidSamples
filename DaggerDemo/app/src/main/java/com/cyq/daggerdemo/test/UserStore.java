package com.cyq.daggerdemo.test;

import android.content.Context;

/**
 * Time: 2019-10-24 16:59
 * Author: ChenYangQi
 * Description:
 */
public class UserStore {


    public void register(Context context) {
        //本地保存数据
        context.getSharedPreferences("user_sp", Context.MODE_PRIVATE);
    }
}
