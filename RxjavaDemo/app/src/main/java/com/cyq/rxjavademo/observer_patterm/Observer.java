package com.cyq.rxjavademo.observer_patterm;

/**
 * Create by 陈扬齐
 * Create on 2019-09-17
 * description:观察者标准
 */
public interface Observer {
    /**
     * 收到被观察者，发生改变
     *
     * @param observableInfo
     * @param <T>
     */
    <T> void changeAction(T observableInfo);
}
