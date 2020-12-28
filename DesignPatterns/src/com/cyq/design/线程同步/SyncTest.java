package src.com.cyq.design.线程同步;

public class SyncTest {

    private static Object object = new Object();

    public synchronized static void staticFun1() {
        System.out.println("static fun1 get lock");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("static fun1 end");
    }

    public synchronized static void staticFun2() {
        System.out.println("static fun2 get lock");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("static fun2 end");
    }

    public static void staticFun3() {
        synchronized (object) {
            System.out.println("static fun3 get lock");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("static fun3 end");
        }
    }

    public synchronized void normalFun1() {
        System.out.println("normal fun1 get lock");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("normal fun1 end");
    }

    public synchronized void normalFun2() {
        System.out.println("normal fun2 get lock");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("normal fun2 end");
    }

    public void normalFun3() {
        synchronized (object) {
            System.out.println("normal fun2 get lock");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("normal fun2 end");
        }
    }
}
