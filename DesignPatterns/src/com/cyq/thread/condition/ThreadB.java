package src.com.cyq.thread.condition;

public class ThreadB extends Thread {

    private MyService myService;

    public ThreadB(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 100; i++) {
            myService.get();
        }
    }
}
