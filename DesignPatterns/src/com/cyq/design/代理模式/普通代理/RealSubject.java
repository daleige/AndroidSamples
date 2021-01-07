package src.com.cyq.design.代理模式.普通代理;

public class RealSubject implements Subject {
    private String name;

    public RealSubject(String name) {
        this.name = name;
    }

    @Override
    public void fun1() {
        System.out.println(name + "执行fun1()");
    }

    @Override
    public void fun2() {
        System.out.println(name + "执行fun2()");
    }
}
