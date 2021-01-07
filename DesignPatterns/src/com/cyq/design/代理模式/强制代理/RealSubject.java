package src.com.cyq.design.代理模式.强制代理;

public class RealSubject implements Subject {
    private final String name;
    private Proxy proxy;

    public RealSubject(String name) {
        this.name = name;
    }

    @Override
    public Proxy getProxy() {
        proxy = new Proxy(this);
        return proxy;
    }

    @Override
    public void fun1() {
        if (proxy == null) {
            System.out.println("请使用指定代理类");
        } else {
            System.out.println(name + "执行了fun1");
        }
    }

    @Override
    public void fun2() {
        if (proxy == null) {
            System.out.println("请使用指定代理类");
        } else {
            System.out.println(name + "执行了fun2");
        }
    }
}
