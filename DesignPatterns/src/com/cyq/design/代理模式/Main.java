package src.com.cyq.design.代理模式;

public class Main {
    public static void main(String[] args) {
        Subject subject = new RealSubject();
        SubjectProxy proxy = new SubjectProxy(subject);
        proxy.fun1();
        proxy.fun2();
    }
}
