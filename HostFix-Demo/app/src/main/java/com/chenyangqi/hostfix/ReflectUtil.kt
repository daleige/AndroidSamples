package com.chenyangqi.hostfix

import java.lang.reflect.Field
import java.lang.reflect.Method

/**
 * 反射工具
 * 用来获取某个私有的字段或者方法
 */
object ReflectUtil {
    /**
     * 获取某个字段，并建他设置为可访问
     */
    fun findField(instance: Any, name: String): Field {
        var clazz = instance.javaClass
        clazz.let {
            try {
                val field = it.getDeclaredField(name)
                if (!field.isAccessible) {
                    field.isAccessible = true
                }
                return field
            } catch (e: NoSuchFieldError) {
                clazz = it.superclass as Class<Any>
            }
        }
        throw NoSuchFieldError("Field not found:$name")
    }

    /**
     * 获取某个方法，并修改他的设置为可访问
     */
    fun findMethod(instance: Any, name: String, vararg paramTypes: Class<*>): Method {
        var clazz = instance.javaClass
        clazz.let {
            try {
                val method = clazz.getDeclaredMethod(name, *paramTypes)
                if (!method.isAccessible) {
                    method.isAccessible = true
                    return method
                }
            } catch (e: NoSuchMethodException) {
                clazz = it.superclass as Class<Any>
            }
        }
        throw NoSuchMethodException("Method not found:$name")
    }
}