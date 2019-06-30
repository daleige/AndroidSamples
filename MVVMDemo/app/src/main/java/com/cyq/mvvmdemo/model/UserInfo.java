package com.cyq.mvvmdemo.model;

import android.databinding.ObservableField;

/**
 * Create by 陈扬齐
 * Create on 2019-06-30
 * description:
 */
public class UserInfo {
    //被观察的属性，必须是public修饰符，因为是DataBinding的规范
    public ObservableField<String> name = new ObservableField<>();

    public ObservableField<String> pwd = new ObservableField<>();
}
