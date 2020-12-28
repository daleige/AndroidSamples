package src.com.cyq.design.线程同步;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockTest {

    public List<Integer> list = new ArrayList<>();
    private final Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        TryLockTest test = new TryLockTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    test.addData();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    test.addData();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(5000);
        for (int i = 0; i < test.list.size(); i++) {
            System.out.print(test.list.get(i) + "\t");
        }
    }

    private void addData() throws InterruptedException {
        //获取锁则正常执行同步块的内容
        if (lock.tryLock()) {
            try {
                System.out.println(Thread.currentThread().getName() + "获取到lock");
                for (int j = 0; j < 10; j++) {
                    Thread.sleep(200);
                    list.add(j);
                }
                System.out.println(Thread.currentThread().getName() + "释放lock");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        } else {
            //为获取到lock则执行其他操作 不继续等待锁释放
            System.out.println(Thread.currentThread().getName() + "未获得锁，执行其他操作！");
        }
    }
}
