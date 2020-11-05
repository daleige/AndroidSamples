package com.cyq.design.线程同步;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {

    public static void main(String[] args) throws InterruptedException {
        UpdateData updateData = new UpdateData();
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 5; j++) {
                        updateData.getData();
                    }
                }
            }).start();
        }

        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 5; j++) {
                        updateData.setData();
                    }
                }
            }).start();
        }

        Thread.sleep(10000);
        System.out.println("最终结果：" + updateData.getResult());
        Thread.sleep(Long.MAX_VALUE);
    }

    static class UpdateData {
        private int data = 0;
        /**
         * ReentrantReadWriteLock写入的时候线程之间互斥，只能被一个线程持有锁
         * 读取的时候可以被多个线程读取
         */
        private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        private void setData() {
            readWriteLock.writeLock().lock();
            try {
                Thread.sleep(20);
                data++;
                System.out.println(Thread.currentThread().getName() + "\t\t写入值:" + data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                readWriteLock.writeLock().unlock();
            }
        }

        private void getData() {
            readWriteLock.readLock().lock();
            try {
                Thread.sleep(20);
                System.out.println(Thread.currentThread().getName() + "\t\t获取值:" + data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                readWriteLock.readLock().unlock();
            }
        }

        private int getResult() {
            return data;
        }
    }
}
