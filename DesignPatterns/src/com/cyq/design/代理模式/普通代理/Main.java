package src.com.cyq.design.代理模式.普通代理;

public class Main {
    public static void main(String[] args) {
        Proxy proxy = new Proxy("李四");
        proxy.fun1();
        proxy.fun2();
    }
}
