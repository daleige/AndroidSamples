package com.cyq.design.线程同步;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
    public static void main(String[] args) throws InterruptedException {
        ConditionTest conditionTest = new ConditionTest();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (finalI % 2 == 0) {
                        conditionTest.changeValue(20, "-");
                    } else {
                        conditionTest.changeValue(10, "+");
                    }
                }
            }).start();
        }

        Thread.sleep(Long.MAX_VALUE);
    }

    private int data = 100;
    private final Lock lock;
    public final Condition condition;

    public ConditionTest() {
        lock = new ReentrantLock();
        condition = lock.newCondition();
    }

    private void changeValue(int value, String option) {
        lock.lock();
        try {
            Thread.sleep(1 * 1000);
            if (option.equals("+")) {
                data = data + value;
                System.out.println(Thread.currentThread().getName() + "\t 加法完成 data：" + data);
                condition.signalAll();
            } else if (option.equals("-")) {
                while (data < value) {
                    System.out.println(Thread.currentThread().getName() + "\t调用了condition.await,当前线程被挂起");
                    condition.await();
                }
                data = data - value;
                System.out.println(Thread.currentThread().getName() + "\tdata减value成功 data：" + data + "减去value：" + value);
                condition.signalAll();
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "\tdata值小于要减去的值 data：" + data + "减去value：" + value);
        } finally {
            lock.unlock();
        }

    }
}
