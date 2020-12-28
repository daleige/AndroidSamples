package src.com.cyq.design.线程同步;

public class WaitTest {
    public static void main(String[] args) throws InterruptedException {
        WaitTest waitTest = new WaitTest();
        for (int i = 0; i < 5; i++) {
            //5个线程中调用了wait方法
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        waitTest.test();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        Thread.sleep(3000);
        //通过一个线程通知notify
        new Thread(new Runnable() {
            @Override
            public void run() {
                waitTest.test2();
            }
        }).start();
    }

    private volatile boolean isWaiting = true;

    private synchronized void test() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " test() start get lock");
        System.out.println(Thread.currentThread().getName() + "test() begin wait");
        while (this.isWaiting) {
            this.wait();
        }
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName() + "test() wait late option");
    }

    private synchronized void test2() {
        System.out.println(Thread.currentThread().getName() + "test2() get lock and begin notifyAll");
        this.isWaiting = false;
        this.notifyAll();
        System.out.println(Thread.currentThread().getName() + "test() end notifyAll");
    }
}
