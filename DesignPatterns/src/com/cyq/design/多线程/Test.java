package com.cyq.design.多线程;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Test {
    public static void main(String[] args) throws InterruptedException {

        Thread thread1=new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });

        Thread thread2=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();

        Thread.sleep(2000);
        thread1.interrupt();
    }
}
