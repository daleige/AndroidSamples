package src.com.cyq.design.线程同步;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    public static void main(String[] args) {
        String str1 = "111111111111111111111111111111111111111111111111111111111111111" +
                "111111111111111111111111111111111111111111111111111111111111111111111111111111111";
        String str2 = "2222222222222222222222222222222222222222222222222222222222222222" +
                "2222222222222222222222222222222222222222222222222222222222222222222222222222222222";
        StringUtil stringUtil = new StringUtil();
        new Thread(new Runnable() {
            @Override
            public void run() {
                stringUtil.printStr(str1);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                stringUtil.printStr(str2);
            }
        }).start();
    }

    static class StringUtil {
        Lock lock = new ReentrantLock();
        private void printStr(String str) {
            lock.lock();
            try {
                for (int i = 0; i < str.length(); i++) {
                    System.out.print(str.charAt(i));
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
