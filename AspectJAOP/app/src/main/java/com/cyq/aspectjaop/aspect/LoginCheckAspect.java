package com.cyq.aspectjaop.aspect;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.cyq.aspectjaop.LoginActivity;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect // 定义切面类
public class LoginCheckAspect {

    private final static String TAG = "netease >>> ";

    // 1、应用中用到了哪些注解，放到当前的切入点进行处理（找到需要处理的切入点）
    // execution，以方法执行时作为切点，触发Aspect类
    // * *(..)) 可以处理ClickBehavior这个类所有的方法
    @Pointcut("execution(@com.cyq.aspectjaop.annotation.LoginCheck * *(..))")
    public void methodPointCut() {}

    // 2、对切入点如何处理
    @Around("methodPointCut()")
    public Object jointPotin(ProceedingJoinPoint joinPoint) throws Throwable {
        Context context = (Context) joinPoint.getThis();
        if (true) { // 从SharedPreferences中读取
            Log.e(TAG, "检测到已登录！");
            return joinPoint.proceed();
        } else {
            Log.e(TAG, "检测到未登录！");
            Toast.makeText(context, "请先登录！", Toast.LENGTH_SHORT).show();
            context.startActivity(new Intent(context, LoginActivity.class));
            return null; // 不再执行方法（切入点）
        }
    }
}
