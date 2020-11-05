package com.cyq.design.线程同步;

public class Test3 {

    public static void main(String[] args) throws InterruptedException {
        SyncTest syncTest=new SyncTest();

        new Thread(new Runnable() {
            @Override
            public void run() {
                SyncTest.staticFun1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                syncTest.staticFun1();
            }
        }).start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                syncTest.normalFun1();
//            }
//        }).start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                syncTest.normalFun2();
//            }
//        }).start();


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                SyncTest.staticFun3();
//            }
//        }).start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                syncTest.normalFun3();
//            }
//        }).start();
        Thread.sleep(Long.MAX_VALUE);
    }
}
