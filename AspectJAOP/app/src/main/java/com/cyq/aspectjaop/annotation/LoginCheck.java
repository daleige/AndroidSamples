package com.cyq.aspectjaop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 用户登录检测
@Target(ElementType.METHOD) // 目标作用在方法之上
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginCheck {
}
