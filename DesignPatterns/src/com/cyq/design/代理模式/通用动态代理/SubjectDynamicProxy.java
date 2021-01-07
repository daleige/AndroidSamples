package src.com.cyq.design.代理模式.通用动态代理;

import java.lang.reflect.InvocationHandler;

public class SubjectDynamicProxy extends DynamicProxy {

    public static <T> T newProxyInstance(T t) {
        ClassLoader classLoader = t.getClass().getClassLoader();
        Class[] classes = {Subject.class};
        InvocationHandler invocationHandler = new MyInvocationHandler(t);
        return (T) newProxyInstance(classLoader, classes, invocationHandler);
    }
}
