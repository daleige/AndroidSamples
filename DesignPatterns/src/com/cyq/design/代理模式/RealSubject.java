package src.com.cyq.design.代理模式;

public class RealSubject implements Subject{
    @Override
    public void fun1() {
        System.out.println(getClass().getSimpleName()+":执行fun1()");
    }

    @Override
    public void fun2() {
        System.out.println(getClass().getSimpleName()+":执行fun2()");
    }
}
