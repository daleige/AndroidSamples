package src.com.cyq.thread.condition;

public class ThreadA extends Thread {

    private MyService myService;

    public ThreadA(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 200; i++) {
            myService.set();
        }
    }
}
