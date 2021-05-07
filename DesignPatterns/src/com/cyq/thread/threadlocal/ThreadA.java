package src.com.cyq.thread.threadlocal;

public class ThreadA extends Thread {
    ThreadLocal<String> threadLocal = new ThreadLocal<>();

    @Override
    public void run() {
        super.run();
        threadLocal.set("线程A的私有数据");
        System.out.println("---" + threadLocal.get());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getThreadLocalValue() {
        return threadLocal.get();
    }
}
