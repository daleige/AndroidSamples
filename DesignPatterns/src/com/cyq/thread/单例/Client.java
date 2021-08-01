package src.com.cyq.thread.单例;

import java.util.concurrent.CountDownLatch;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        for (; ; ) {
            CountDownLatch latch = new CountDownLatch(1);
            CountDownLatch end = new CountDownLatch(200);
            for (int i = 0; i < 200; i++) {
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        try {
                            latch.await();
                            MyService myService = MyService.getInstance();
                            if (myService.number == 0) {
                                System.out.println("MyService.number==0 进程结束--"+currentThread().getName());
                                System.exit(0);
                            }
                            end.countDown();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();
            }
            latch.countDown();
            end.await();
            MyService.reset();
        }
    }
}
