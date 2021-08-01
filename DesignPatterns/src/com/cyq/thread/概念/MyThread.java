package src.com.cyq.thread.概念;

public class MyThread extends Thread {

    public MyThread() {

    }

    @Override
    public void run() {
        super.run();
        try {
            for (int i = 0; i < 500000; i++) {
                if (interrupted()) {
                    System.out.println("---interrupted:" + interrupted());
                    throw new InterruptedException();
                }
                System.out.println("i:" + i);
            }
            System.out.println("finally-----");
        } catch (InterruptedException e) {
            System.out.println("InterruptedException-----");
            e.printStackTrace();
        }
    }
}
