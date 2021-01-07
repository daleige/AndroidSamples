package src.com.cyq.design.代理模式.通用动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class DynamicProxy<T> {

    public static <T> T newProxyInstance(ClassLoader loader,
                                         Class<?>[] interfaces,
                                         InvocationHandler h) {
        //切入点
        if (true) {
            //通知
            new BeforeAdvice().exec();
        }
        return (T) Proxy.newProxyInstance(loader, interfaces, h);
    }
}
