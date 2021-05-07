package src.com.cyq.thread.概念;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("runnable开始---");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("runnable结束---");
    }
}
