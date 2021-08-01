package src.com.cyq.thread.notify;

public class MyServer {

    private final Object lock = new Object();
    private final MyList myList = new MyList();

    public void waitMethod() {
        synchronized (lock) {
            try {
                if (myList.size() != 5) {
                    System.out.println("线程" + Thread.currentThread().getName() + "开始等待");
                    lock.wait();
                    System.out.println("线程" + Thread.currentThread().getName() + "结束等待");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void notifyMethod() {
        synchronized (lock) {
            myList.clear();
            for (int i = 0; i < 10; i++) {
                myList.add();
                if (myList.size() == 5) {
                    //System.out.println("线程" + Thread.currentThread().getName() + "发出notify通知");
                    lock.notify();
                }
                //System.out.println("线程" + Thread.currentThread().getName() + "执行次数：" + i);
            }
            //System.out.println("线程" + Thread.currentThread().getName() + "执行结束");
        }
    }

    public void notifyAllMethod(){
        synchronized (lock){
            lock.notifyAll();
        }
    }
}
