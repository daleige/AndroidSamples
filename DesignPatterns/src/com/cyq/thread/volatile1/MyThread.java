package src.com.cyq.thread.volatile1;

public class MyThread extends Thread {

    @Override
    public void run() {
        super.run();
        synchronized (MyThread.class) {
            for (int i = 0; i < 100; i++) {
                Data.count++;
            }
        }
    }
}
