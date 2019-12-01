package com.cyq.daggerdemo.demo;

import dagger.Module;
import dagger.Provides;

/**
 * Time: 2019-10-24 18:30
 * Author: ChenYangQi
 * Description:
 */
@Module
public class PersonModule {
    Man mMan;
    int mAge;

    public PersonModule(Man man, int age) {
        this.mAge = age;
        this.mMan = man;
    }

//    @Provides
//    public int provideAge() {
//        return mAge;
//    }
//
//    @Provides
//    public Man provideMan() {
//        return mMan;
//    }

    @Provides
    public Person providePerson(Man man, int age) {
        return new Person(mMan, mAge);
    }
}
