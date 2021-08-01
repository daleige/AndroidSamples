package src.com.cyq.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyServer {
    public static int count = 0;
    private final Lock LOCK = new ReentrantLock();
    private final Condition CONDITION = LOCK.newCondition();

    public void waitMethod() {
        try {
            LOCK.lock();
            System.out.println("condition.wait():" + System.currentTimeMillis());
            CONDITION.await();
            for (int i = 0; i < 5000; i++) {
                count++;
            }
            System.out.println("执行结束--");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }
    }

    public void singleMethod() {
        try {
            LOCK.lock();
            System.out.println("condition.single():" + System.currentTimeMillis());
            CONDITION.signal();
        } finally {
            LOCK.unlock();
        }
    }
}
