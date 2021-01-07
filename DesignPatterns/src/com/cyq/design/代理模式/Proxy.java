package src.com.cyq.design.代理模式;

public class Proxy implements Subject {
    private Subject subject;

    public Proxy() {
        subject = new RealSubject();
    }

    public Proxy(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void fun1() {
        this.subject.fun1();
    }

    @Override
    public void fun2() {
        this.subject.fun2();
    }
}
