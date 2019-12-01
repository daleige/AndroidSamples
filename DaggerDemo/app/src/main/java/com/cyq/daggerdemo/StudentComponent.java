package com.cyq.daggerdemo;

import dagger.Component;

/**
 * Time: 2019-10-23 23:29
 * Author: ChenYangQi
 * Description:
 */
@Component(modules = StudentModule.class)
public interface StudentComponent {

    //注入到的目标
//    void inject(MainActivity mainActivity);
}
