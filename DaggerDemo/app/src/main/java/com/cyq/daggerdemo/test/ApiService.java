package com.cyq.daggerdemo.test;

import android.util.Log;

import javax.inject.Inject;

/**
 * Time: 2019-10-24 16:59
 * Author: ChenYangQi
 * Description:
 */
public class ApiService {

    @Inject
    public ApiService(){

    }

    public void register() {
        //服务端保存数据
        Log.i("test", "dagger注入成功");
    }
}
