package src.com.cyq.thread.synchronize;

public class Test2 {

    synchronized public static void testMethod() {
        int a = 100;
    }

    public void testMethod2() {
        synchronized (this) {
            int a = 200;
        }
    }

    private int b = 10;

    public static void main(String[] args) {
        testMethod();
    }

    public void test(String str) {
        if (str != null) {
            synchronized (this) {
                int a = 0;
                String aa = str;
            }

        }
    }
}
