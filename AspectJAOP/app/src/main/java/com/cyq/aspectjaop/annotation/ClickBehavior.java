package com.cyq.aspectjaop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 用户点击痕迹（行为统计）  IoC容器
@Target(ElementType.METHOD) // 目标作用在方法之上
@Retention(RetentionPolicy.RUNTIME)
public @interface ClickBehavior {
    String value();
}
