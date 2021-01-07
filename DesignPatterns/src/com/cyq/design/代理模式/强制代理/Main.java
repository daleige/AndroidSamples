package src.com.cyq.design.代理模式.强制代理;

public class Main {
    public static void main(String[] args) {
        System.out.println("------------------------");
        Subject subject1 = new RealSubject("李四");
        subject1.fun1();
        subject1.fun2();

        System.out.println("------------------------");
        Subject subject2 = new RealSubject("张三");
        Proxy proxy2 = new Proxy(subject2);
        proxy2.fun1();
        proxy2.fun2();

        System.out.println("------------------------");
        Subject subject3 = new RealSubject("王五");
        Proxy proxy3 = subject3.getProxy();
        proxy3.fun1();
        proxy3.fun2();
    }
}
