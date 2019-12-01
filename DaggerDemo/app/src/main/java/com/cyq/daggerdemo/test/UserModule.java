package com.cyq.daggerdemo.test;

import dagger.Module;
import dagger.Provides;

/**
 * Time: 2019-10-24 17:23
 * Author: ChenYangQi
 * Description:
 */
@Module
public class UserModule {

    /**
     * 提供构造方法的参数
     *
     * @return
     */
//    @Provides
//    public ApiService provideApiService() {
//        return new ApiService();
//    }

    @Provides
    public UserManager provideUserManager(ApiService apiService) {
        return new UserManager(apiService);
    }
}
