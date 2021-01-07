package src.com.cyq.design.代理模式.通用动态代理;

import java.lang.reflect.InvocationHandler;

public class SubjectDynamicProxy extends DynamicProxy {

    public static <T> T newProxyInstance(T t,Class cls) {
        ClassLoader classLoader = t.getClass().getClassLoader();
        Class[] classes = {cls};
        InvocationHandler invocationHandler = new MyInvocationHandler(t);
        return (T) newProxyInstance(classLoader, classes, invocationHandler);
    }
}
