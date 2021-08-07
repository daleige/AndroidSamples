package com.chenyangqi.hostfix

import java.lang.reflect.Field
import java.lang.reflect.Method

/**
 * 反射工具
 * 用来获取某个私有的字段或者方法
 */
object ReflectUtil {
    /**
     * 获取某个字段，并将它设置为可访问
     */
    fun findField(instance: Any, name: String): Field {
        var clazz = instance.javaClass
        while (clazz != null) {
            try {
                val field = clazz.getDeclaredField(name)
                if (!field.isAccessible) {
                    field.isAccessible = true
                }
                return field
            } catch (e: NoSuchFieldException) {
                clazz = clazz.superclass as Class<Any>
            }
        }
        throw NoSuchFieldException("Field not found: $name")
    }

    /**
     * 反射获取某个方法，并将它设置为可访问
     */
    fun findMethod(
        instance: Any,
        name: String,
        vararg paramType: Class<*>?
    ): Method {
        var clazz = instance.javaClass
        while (clazz != null) {
            try {
                val method = clazz.getDeclaredMethod(name, *paramType)
                if (!method.isAccessible) {
                    method.isAccessible = true
                }
                return method
            } catch (e: NoSuchMethodException) {
                clazz = clazz.superclass as Class<Any>
            }
        }
        throw NoSuchMethodException("Method not found: $name")
    }
}