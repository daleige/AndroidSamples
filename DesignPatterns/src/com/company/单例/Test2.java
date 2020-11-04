package src.com.company.单例;

public class Test2 {
    public static void sleep1() throws InterruptedException {
        synchronized (Test2.class) {
            System.out.println(Thread.currentThread().getName() + "  sleep1 start");
            Thread.sleep(5 * 1000);
            System.out.println(Thread.currentThread().getName() + "  sleep1 end");

        }
    }

    public synchronized void sleep2() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "  sleep2 start");
        Thread.sleep(5 * 1000);
        System.out.println(Thread.currentThread().getName() + "  sleep2 end");
    }

    public synchronized void sleep3() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "  sleep3 start");
        Thread.sleep(5 * 1000);
        System.out.println(Thread.currentThread().getName() + "  sleep3 end");
    }
}
