package src.com.cyq.design.代理模式.动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args) {
        Subject subject = new RealSubject("张三");
        InvocationHandler handler = new SubjectIH(subject);
        //获得ClassLoader
        ClassLoader cl = subject.getClass().getClassLoader();
        //动态产生一个代理者
        Subject proxy = (Subject) Proxy.newProxyInstance(cl, new Class[]{Subject.class}, handler);

        //执行代理操作
        proxy.fun1();
        proxy.fun2("hello world");
        String str = proxy.fun3();
        System.out.println(str);
    }
}
