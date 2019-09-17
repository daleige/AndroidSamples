package com.cyq.rxjavademo.observer_patterm;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by 陈扬齐
 * Create on 2019-09-17
 * description:被观察者
 */
public class ObservableImpl implements Observable {
    private List<Observer> observerList=new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for (Observer observer:observerList){
            //在被观察者实现类中，通知所有注册了的观察者
            observer.changeAction("接收到通知");
        }
    }
}
