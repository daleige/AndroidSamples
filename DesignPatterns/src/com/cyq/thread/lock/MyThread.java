package src.com.cyq.thread.lock;

public class MyThread extends Thread{

    private MyServer server;

    public MyThread(MyServer server) {
        this.server = server;
    }

    @Override
    public void run() {
        super.run();
        server.waitMethod();
    }
}
