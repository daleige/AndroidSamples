package com.cyq.rxjavademo.observer_patterm;

/**
 * Create by 陈扬齐
 * Create on 2019-09-17
 * description:被观察者标准
 */
public interface Observable {
    /**
     * 在被观察中注册观察者
     */
    void registerObserver(Observer observer);

    /**
     * 在被观察者中移除观察者
     */
    void removeObserver(Observer observer);

    /**
     * 在被观察者中通知所有注册的观察者
     */
    void notifyObserver();
}
