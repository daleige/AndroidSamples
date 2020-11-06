package com.cyq.design.单例;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        //测试在不同的线程中创建多个对象是否是唯一的实例
        Executor executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    //测试饿汉式单例
//                    Singleton1 singleton1 = Singleton1.getInstance();
//                    Singleton1 singleton2 = Singleton1.getInstance();
//                    System.out.println(singleton1.hashCode() + "\t-----\t" + singleton2.hashCode());

                    //懒汉式 线程不安全
                    SingLeton2 singLeton21=SingLeton2.getInstance();
                    SingLeton2 singLeton22=SingLeton2.getInstance();
                    System.out.println(singLeton21.hashCode() + "\t-----\t" + singLeton22.hashCode());

                    //懒汉式，线程安全写法
//                    Singleton3 singleton31 = Singleton3.getINSTANCE();
//                    Singleton3 singleton32 = Singleton3.getINSTANCE();
//                    System.out.println(singleton31.hashCode() + "\t-----\t" + singleton32.hashCode());
//
//                    Singleton4 singleton41 = Singleton4.getInstance();
//                    Singleton4 singleton42 = Singleton4.getInstance();
//                    System.out.println(singleton41.hashCode() + "\t-----\t" + singleton42.hashCode());

//                    Singleton6 singleton61 = Singleton6.INSTANCE;
//                    Singleton6 singleton62 = Singleton6.INSTANCE;
//                    System.out.println(singleton61.hashCode() + "\t-----\t" + singleton62.hashCode());
                }
            });
        }


//        Test2 test2 = new Test2();
//        new Thread() {
//            @Override
//            public void run() {
//                super.run();
//                try {
//                    test2.sleep2();
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
//
//        new Thread() {
//            @Override
//            public void run() {
//                super.run();
//                try {
//                    test2.sleep3();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
//
//        new Thread() {
//            @Override
//            public void run() {
//                super.run();
//
//                Singleton4.getInstance();
//
//            }
//        }.start();
    }

}
