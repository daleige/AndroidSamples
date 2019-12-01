package com.cyq.daggerdemo.demo;

import android.util.Log;

/**
 * Time: 2019-10-24 18:29
 * Author: ChenYangQi
 * Description:
 */
public class Person {

    private Man mMan;
    private int age;

    public Person(Man mMan, int age) {
        this.mMan = mMan;
        this.age = age;
    }

    public void info() {
        Log.i("test", "性别：" + mMan.sex + "--年龄：" + age);
    }
}
