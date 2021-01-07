package src.com.cyq.design.代理模式;

public class SubjectProxy implements Subject {
    private Subject subject;

    public SubjectProxy() {
        subject = new RealSubject();
    }

    public SubjectProxy(Subject subject) {
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
