package src.com.cyq.design.代理模式;

public class Main {
    public static void main(String[] args) {
        Subject subject = new RealSubject();
        Proxy proxy = new Proxy(subject);
        proxy.fun1();
        proxy.fun2();
    }
}
