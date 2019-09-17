package com.cyq.rxjavademo.observer_patterm;

import android.util.Log;

/**
 * Create by 陈扬齐
 * Create on 2019-09-17
 * description:被观察者实现类
 */
public class ObserverImpl implements Observer {

    private String observerName;

    public ObserverImpl(String observerName) {
        this.observerName = observerName;
    }

    @Override
    public <T> void changeAction(T observableInfo) {
        Log.i("test",observerName + observableInfo);
    }
}
