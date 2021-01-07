package src.com.cyq.design.代理模式.动态代理;

public class RealSubject implements Subject {
    private final String name;

    public RealSubject(String name) {
        this.name = name;
    }

    @Override
    public void fun1() {
        System.out.println(name + "执行了fun1()");
    }

    @Override
    public void fun2(String str) {
        System.out.println(name + "执行了fun2(" + str + ")");
    }

    @Override
    public String fun3() {
        System.out.println(name + "执行了fun3()");
        return name;
    }
}
