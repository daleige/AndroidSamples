package src.com.cyq.design.代理模式.通用动态代理;

public class Real2Subject implements Subject {
    @Override
    public void doSomething() {
        System.out.println("Real2Subject do something....");
    }
}
