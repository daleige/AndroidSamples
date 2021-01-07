package src.com.cyq.design.代理模式.动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SubjectIH implements InvocationHandler {
    Object obj;

    public SubjectIH(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equalsIgnoreCase("fun1")) {
            System.out.println("调用fun1()前....");
        }
        return method.invoke(this.obj, args);
    }
}
