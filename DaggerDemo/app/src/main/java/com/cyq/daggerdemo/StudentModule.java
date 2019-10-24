package com.cyq.daggerdemo;

import dagger.Module;
import dagger.Provides;

/**
 * Time: 2019-10-23 23:14
 * Author: ChenYangQi
 * Description:
 */
@Module
public class StudentModule {

    @Provides
    public Student getStudent() {
        return new Student("");
    }
}
