package src.com.cyq.thread.threadlocal;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        ThreadA threadA = new ThreadA();
        threadA.start();

        ThreadB threadB = new ThreadB();
        threadB.start();

        Thread.sleep(1000);
        ThreadLocal<String> stringThreadLocal=new ThreadLocal<>();
        stringThreadLocal.set("Main线程的数据");
        System.out.println(threadA.getThreadLocalValue());
        System.out.println(threadB.getThreadLocalValue());
    }
}
