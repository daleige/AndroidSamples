package com.chenyangqi.router.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author : ChenYangQi
 * date   : 6/20/21 11:43
 * desc   : 路由注解器
 */
/**
 * 说明当前注解可以修饰的元素，此处表示可以用于标记在类上面
 */
@Target({ElementType.TYPE})
/**
 * 说明当前注解可以被保留的时间
 */
@Retention(RetentionPolicy.CLASS)
public @interface Destination {
    /**
     * 路由路径
     *
     * @return
     */
    String url();

    /**
     * 页面中文描述
     *
     * @return
     */
    String description();
}
