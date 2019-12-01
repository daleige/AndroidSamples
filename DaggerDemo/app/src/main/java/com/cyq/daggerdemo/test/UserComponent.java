package com.cyq.daggerdemo.test;

import com.cyq.daggerdemo.MainActivity;

import dagger.Component;

/**
 * Time: 2019-10-24 17:23
 * Author: ChenYangQi
 * Description:
 */
@Component(modules = {UserModule.class})
public interface UserComponent {

//    void inject(MainActivity activity);

}
