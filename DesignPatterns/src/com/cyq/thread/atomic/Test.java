package src.com.cyq.thread.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class Test {

    public static void main(String[] args) {

        AtomicInteger a = new AtomicInteger(22);
        System.out.println(a.addAndGet(-10));
        System.out.println(a.incrementAndGet());
        System.out.println(a.get());
        System.out.println(a.compareAndSet(33,a.get()));

    }
}
