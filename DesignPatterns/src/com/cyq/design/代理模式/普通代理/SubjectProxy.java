package src.com.cyq.design.代理模式.普通代理;

/**
 * 普通代理模式，不允许调用类直接生成Subject，所以可以通过代理类生成Subject
 */
public class SubjectProxy implements Subject {
    private Subject subject;

    public SubjectProxy(String name) {
        subject = new RealSubject(name);
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
