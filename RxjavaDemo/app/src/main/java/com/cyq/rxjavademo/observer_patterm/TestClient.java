package com.cyq.rxjavademo.observer_patterm;

/**
 * Create by 陈扬齐
 * Create on 2019-09-17
 * description:测试类
 */
public class TestClient {

    public static void main(String[] args) {
        Observer observer1 = new ObserverImpl("观察者1");
        Observer observer2 = new ObserverImpl("观察者2");
        Observer observer3 = new ObserverImpl("观察者3");
        Observer observer4 = new ObserverImpl("观察者4");

        Observable observable = new ObservableImpl();

        observable.registerObserver(observer1);
        observable.registerObserver(observer2);
        observable.registerObserver(observer3);
        observable.registerObserver(observer4);

        observable.notifyObserver();
    }
}
