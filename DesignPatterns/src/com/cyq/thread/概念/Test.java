package src.com.cyq.thread.概念;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        MyThread thread = new MyThread();
        thread.start();
//        Thread.sleep(500);
//        thread.interrupt();
//        System.out.println("000000000000");
        Thread.sleep(100);
        thread.suspend();
        Thread.sleep(3000);
        thread.resume();
        Thread.yield();
    }
}
