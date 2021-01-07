package src.com.cyq.design.代理模式.强制代理;

public class Proxy implements Subject {
    private Subject subject;

    public Proxy(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void fun1() {
        subject.fun1();
    }

    @Override
    public void fun2() {
        subject.fun2();
    }

    @Override
    public Proxy getProxy() {
        return null;
    }
}
