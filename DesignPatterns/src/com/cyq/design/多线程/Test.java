package com.cyq.design.多线程;

public class Test {
    //https://www.cnblogs.com/onlywujun/p/3565082.html
    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "线程 start");
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        Thread.sleep(900);
                        System.out.println(Thread.currentThread().getName() + "执行 io 操作....");
                    } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread().getName() + "收到中断命令了---");
                        return;
                    }
                }
                System.out.println(Thread.currentThread().getName() + "线程 end");
            }
        });

        thread1.start();
        Thread.sleep(3000);
        thread1.interrupt();
    }
}
