package src.com.cyq.design.线程同步;

import com.sun.xml.internal.ws.message.stream.StreamHeader;

public class ThreadLocalTest {
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("aaaa");
        threadLocal.set("bbbb");
        System.out.println(threadLocal.get());
    }
}
