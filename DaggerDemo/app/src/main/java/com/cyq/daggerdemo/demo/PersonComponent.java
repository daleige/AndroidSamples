package com.cyq.daggerdemo.demo;

import com.cyq.daggerdemo.MainActivity;

import dagger.Component;

/**
 * Time: 2019-10-24 18:30
 * Author: ChenYangQi
 * Description:
 */
@Component(modules = {PersonModule.class})
public interface PersonComponent {

    void inject(MainActivity mainActivity);
}
