package src.com.cyq.thread.守护线程;

public class Test {
    public static void main(String[] args) {

        try {
            DaemonThread daemonThread = new DaemonThread();
            daemonThread.setDaemon(true);
            daemonThread.start();
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
