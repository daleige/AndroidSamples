package com.cyq.design.多线程;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Test {
    //https://www.cnblogs.com/onlywujun/p/3565082.html
    public static void main(String[] args) throws InterruptedException {

        Thread thread1=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"线程 start");
                while (!Thread.currentThread().isInterrupted()){
                    System.out.println(Thread.currentThread().getName()+"线程被中断");
                }
                System.out.println(Thread.currentThread().getName()+"线程 end");
            }
        });

        thread1.start();
        Thread.sleep(2000);
        thread1.interrupt();
    }
}
