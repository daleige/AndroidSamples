package src.com.company.单例;

public class Test3 {

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                A.s1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                new A().i1();
            }
        }).start();

        Thread.sleep(Integer.MAX_VALUE);

    }

    static class A {

        private static Object obj = new Object();

        public static void s1() {
            synchronized (obj) {
                System.out.println("static s1 get lock");
                try {
                    Thread.sleep(3 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("static s2 release lock");
            }
        }

        public static void s2() {
            synchronized (A.class) {
                System.out.println("static s2 get lock");
                try {
                    Thread.sleep(3 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("static s2 release lock");
            }
        }

        public void i1() {
            synchronized (obj) {
                System.out.println("instance i1 get lock");
                try {
                    Thread.sleep(3 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("instance i1 release lock");
            }
        }


    }
}
