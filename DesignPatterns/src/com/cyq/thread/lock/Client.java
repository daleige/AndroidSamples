package src.com.cyq.thread.lock;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        MyServer server=new MyServer();
//        for (int i = 0; i < 10; i++) {
//            MyThread thread=new MyThread(server);
//            thread.start();
//        }
//
//        try {
//            Thread.sleep(3_000);
//            System.out.println("最终结果："+MyServer.count);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        MyThread thread=new MyThread(server);
        thread.start();

        Thread.sleep(2_000);
        server.singleMethod();
    }
}
