package src.com.cyq.thread.优先级;

import java.util.Random;

public class Thread1 extends Thread {

    @Override
    public void run() {
        super.run();
        long startTime = System.currentTimeMillis();
        long addResult = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 500000; j++) {
                Random random = new Random();
                random.nextInt();
                addResult = addResult + i;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Thread1执行结束，耗时：" + (endTime - startTime));
    }
}
