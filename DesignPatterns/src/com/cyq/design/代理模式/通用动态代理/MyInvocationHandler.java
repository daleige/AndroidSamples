package src.com.cyq.design.代理模式.通用动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {
    private final Object obg;

    public MyInvocationHandler(Object obg) {
        this.obg = obg;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(obg, args);
    }
}
