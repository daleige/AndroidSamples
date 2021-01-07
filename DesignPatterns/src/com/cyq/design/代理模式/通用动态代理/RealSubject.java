package src.com.cyq.design.代理模式.通用动态代理;

public class RealSubject implements Subject {
    @Override
    public void doSomething() {
        System.out.println("do Something ......");
    }
}
