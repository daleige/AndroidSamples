package src.com.cyq.thread.守护线程;

public class DaemonThread extends Thread {

    @Override
    public void run() {
        super.run();
        int i = 1;
        while (true) {
            i++;
            System.out.println("-----i:" + i);
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
