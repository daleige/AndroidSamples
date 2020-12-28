package src.com.cyq.design.线程同步;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockInterrupt {
    private final Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        LockInterrupt lockInterrupt = new LockInterrupt();
        TestThread test1 = new TestThread(lockInterrupt);
        TestThread test2 = new TestThread(lockInterrupt);
        test1.start();
        test2.start();
        Thread.sleep(2000);
        //主动中断
        test2.interrupt();
    }

    private void doSomething() throws InterruptedException {
        //线程可以调用interrupt中断，会抛出InterruptedException
        System.out.println(Thread.currentThread().getName() + "\t\t等待获取lock");
        lock.lockInterruptibly();
        try {
            System.out.println(Thread.currentThread().getName() + "\t\t获取到lock");
            Thread.sleep(10 * 1000);
            System.out.println(Thread.currentThread().getName() + "\t\t执行完成");
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "\t\t调用interrupt()中断等待获得lock");
        } finally {
            System.out.println(Thread.currentThread().getName() + "\t\t释放lock");
            lock.unlock();
        }
    }

    static class TestThread extends Thread {
        private LockInterrupt lockInterrupt = null;

        public TestThread(LockInterrupt lockInterrupt) {
            this.lockInterrupt = lockInterrupt;
        }

        @Override
        public void run() {
            super.run();
            try {
                lockInterrupt.doSomething();
            } catch (InterruptedException e) {

            }
        }
    }
}

