package com.cyq.rxjavademo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.cyq.rxjavademo.observer_patterm.ObservableImpl;
import com.cyq.rxjavademo.observer_patterm.Observer;
import com.cyq.rxjavademo.observer_patterm.ObserverImpl;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test();
    }

    private void test() {
        Observer observer1 = new ObserverImpl("观察者1");
        Observer observer2 = new ObserverImpl("观察者2");
        Observer observer3 = new ObserverImpl("观察者3");
        Observer observer4 = new ObserverImpl("观察者4");


        com.cyq.rxjavademo.observer_patterm.Observable observable = new ObservableImpl();

        observable.registerObserver(observer1);
        observable.registerObserver(observer2);
        observable.registerObserver(observer3);
        observable.registerObserver(observer4);

        observable.notifyObserver();
    }

    public void fun1() {
//        //起点 被观察者
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//
//            }
//        }).subscribe(//订阅
//                //终点 被观察者
//                new Observer<Integer>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Integer integer) {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
    }
}
