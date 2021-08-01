package src.com.cyq.thread.优先级;

public class Test {

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            Thread2 thread2 = new Thread2();
            thread2.setPriority(Thread.MIN_PRIORITY);
            thread2.start();

            Thread1 thread1 = new Thread1();
            thread1.setPriority(Thread.MAX_PRIORITY);
            thread1.start();
        }
    }
}
