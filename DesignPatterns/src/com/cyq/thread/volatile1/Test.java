package src.com.cyq.thread.volatile1;

public class Test {

    public static void main(String[] args) {
        MyThread[] threads = new MyThread[100];
        for (int i = 0; i < 100; i++) {
            threads[i] = new MyThread();
        }

        for (int i = 0; i < 100; i++) {
            threads[i].start();
        }

        try {
            Thread.sleep(3_000);
            System.out.println("计算结果："+Data.count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
