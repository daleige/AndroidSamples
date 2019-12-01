package com.cyq.daggerdemo.test;

import android.content.Context;

/**
 * Time: 2019-10-24 16:57
 * Author: ChenYangQi
 * Description:
 */
public class UserManager {

    private ApiService mApiService;
    private UserStore mUserStore;

    public UserManager(ApiService apiService) {
        mApiService = apiService;
    }

    public void register(Context context){
        mApiService.register();
    }
}
