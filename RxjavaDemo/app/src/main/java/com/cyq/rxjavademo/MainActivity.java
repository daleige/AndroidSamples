package com.cyq.rxjavademo;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.cyq.rxjavademo.observer_patterm.ObservableImpl;
import com.cyq.rxjavademo.observer_patterm.ObserverImpl;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class MainActivity extends AppCompatActivity {
    String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fun5();
    }

    private void test() {
        com.cyq.rxjavademo.observer_patterm.Observer observer1 = new ObserverImpl("观察者1");
        com.cyq.rxjavademo.observer_patterm.Observer observer2 = new ObserverImpl("观察者2");
        com.cyq.rxjavademo.observer_patterm.Observer observer3 = new ObserverImpl("观察者3");
        com.cyq.rxjavademo.observer_patterm.Observer observer4 = new ObserverImpl("观察者4");


        com.cyq.rxjavademo.observer_patterm.Observable observable = new ObservableImpl();

        observable.registerObserver(observer1);
        observable.registerObserver(observer2);
        observable.registerObserver(observer3);
        observable.registerObserver(observer4);

        observable.notifyObserver();
    }

    public void fun1() {
        //起点 被观察者
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {

            }
        }).subscribe(//订阅
                //终点 被观察者
                new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void fun4() {
        //上游 Observable 被观察者
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {

                Log.d(TAG, "step2:上游 subscribe : 开始发射...");
                emitter.onNext("RxJavaStudy");// todo 2

                emitter.onComplete();//todo 4
                Log.d(TAG, "step5:上游 subscribe : 发射完成...");
            }
        }).subscribe(
                //下游 Observer 观察者
                new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //弹出加载框
                        Log.d(TAG, "step1:上游和下游订阅成功 onSubscribe 1");//todo 1
                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "step3:下游接收 onNext " + s); //todo 3
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        //关闭加载框
                        Log.d(TAG, "step4:下游接收完成 onComplete "); //todo 5
                    }
                });
    }

    Disposable disposable;

    /**
     * 切断下游测试
     */
    public void fun5() {
        //上游 Observable 被观察者
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; i < 100; i++) {
                    emitter.onNext(i);
                }
            }
        }).subscribe(
                //下游 Observer 观察者
                new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        MainActivity.this.disposable = d;
                    }

                    @Override
                    public void onNext(Integer i) {
                        Log.d(TAG, "下游接收 onNext " + i);
                        if (i == 12 && disposable != null) {
                            disposable.dispose();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null) {
            disposable.dispose();
        }
    }
}



