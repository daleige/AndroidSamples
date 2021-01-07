package src.com.cyq.design.代理模式.通用动态代理;

public class Main {
    public static void main(String[] args) {
        //常规使用
       /* Subject subject = new RealSubject();
        InvocationHandler handler = new MyInvocationHandler(subject);
        Subject proxy = (Subject) Proxy.newProxyInstance(
                subject.getClass().getClassLoader(),
                new Class[]{Subject.class},
                handler
        );
        proxy.doSomething();*/

        //DynamicProxy方式使用
        /*Subject subject = new RealSubject();
        InvocationHandler handler = new MyInvocationHandler(subject);
        Subject proxy = DynamicProxy.newProxyInstance(
                subject.getClass().getClassLoader(),
                new Class[]{Subject.class},
                handler
        );
        proxy.doSomething();*/

        //通用方式实现
        Subject subject = new RealSubject();
        Subject proxy = SubjectDynamicProxy.newProxyInstance(subject, Subject.class);
        proxy.doSomething();

        Subject subject2=new Real2Subject();
        Subject proxy2=SubjectDynamicProxy.newProxyInstance(subject2,Subject.class);
        proxy2.doSomething();
    }
}
