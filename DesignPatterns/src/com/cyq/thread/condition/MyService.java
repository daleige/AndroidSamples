package src.com.cyq.thread.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyService {

    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private boolean haveValue = true;

    public void set() {
        try {
            lock.lock();
            while (haveValue) {
                System.out.println("连续生产-----");
                condition.await();
            }
            System.out.println("生产者-----");
            haveValue = true;
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void get() {
        try {
            lock.lock();
            while (!haveValue) {
                condition.await();
            }
            System.out.println("消费者*****");
            haveValue = false;
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
