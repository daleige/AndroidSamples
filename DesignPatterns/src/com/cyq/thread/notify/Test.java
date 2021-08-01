package src.com.cyq.thread.notify;

import javax.xml.ws.Service;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        MyServer myServer = new MyServer();
//        ThreadA threadA=new ThreadA(myServer);
//        ThreadB threadB=new ThreadB(myServer);
//        threadA.start();
//        try {
//            ThreadA.sleep(2_000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        threadB.start();

        for (int i = 0; i < 10; i++) {
            ThreadA threadA = new ThreadA(myServer);
            threadA.start();
        }
        //测试依次唤醒wait
//        for (int i = 0; i < 5; i++) {
//            ThreadB threadB = new ThreadB(myServer);
//            Thread.sleep(1000);
//            threadB.start();
//        }

        //唤醒所有wait
        ThreadC threadC=new ThreadC(myServer);
        Thread.sleep(1000);
        threadC.start();

    }

    static class ThreadA extends Thread {

        private MyServer service;

        public ThreadA(MyServer service) {
            this.service = service;
        }

        @Override
        public void run() {
            super.run();
            service.waitMethod();
        }
    }

    static class ThreadB extends Thread {

        private MyServer service;

        public ThreadB(MyServer service) {
            this.service = service;
        }

        @Override
        public void run() {
            super.run();
            service.notifyMethod();
        }
    }

    static class ThreadC extends Thread {

        private MyServer service;

        public ThreadC(MyServer service) {
            this.service = service;
        }

        @Override
        public void run() {
            super.run();
            service.notifyAllMethod();
        }
    }
}
