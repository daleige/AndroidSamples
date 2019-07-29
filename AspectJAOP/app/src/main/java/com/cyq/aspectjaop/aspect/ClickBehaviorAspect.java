package com.cyq.aspectjaop.aspect;

import android.util.Log;

import com.cyq.aspectjaop.annotation.ClickBehavior;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect // 定义切面类
public class ClickBehaviorAspect {

    private final static String TAG = "netease >>> ";

    // 1、应用中用到了哪些注解，放到当前的切入点进行处理（找到需要处理的切入点）
    // execution，以方法执行时作为切点，触发Aspect类
    // * *(..)) 可以处理ClickBehavior这个类所有的方法
    @Pointcut("execution(@com.cyq.aspectjaop.annotation.ClickBehavior * *(..))")
    public void methodPointCut() {}

    // 2、对切入点如何处理
    @Around("methodPointCut()")
    public Object jointPotin(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取签名方法
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        // 获取方法所属的类名
        String className = methodSignature.getDeclaringType().getSimpleName();

        // 获取方法名
        String methodName = methodSignature.getName();

        // 获取方法的注解值(需要统计的用户行为)
        String funName = methodSignature.getMethod().getAnnotation(ClickBehavior.class).value();

        // 统计方法的执行时间、统计用户点击某功能行为。（存储到本地，每过x天上传到服务器）
        long begin = System.currentTimeMillis();
        Log.e(TAG, "ClickBehavior Method Start >>> ");
        Object result = joinPoint.proceed(); // MainActivity中切面的方法
        long duration = System.currentTimeMillis() - begin;
        Log.e(TAG, "ClickBehavior Method End >>> ");
        Log.e(TAG, String.format("统计了：%s功能，在%s类的%s方法，用时%d ms",
                funName, className, methodName, duration));

        return result;
    }
}
