package com.chenyangqi.router_runtime;

import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author chenyq113@midea.com
 * @describe xxx
 * @time 2021/7/14 10:23
 */
public class ReflectUtil {

    public static Map<String, String> getRouterMap() {
        try {
            Class<?> clazz = Class.forName("com.chenyangqi.router.mapping.generated.RouterMapping");
            Method getMethod = clazz.getDeclaredMethod("get");
            Map<String, String> map = (Map<String, String>) getMethod.invoke(null);
            return map;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Log.e("RouterTAG", "找不到类");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            Log.e("RouterTAG", "找不到方法");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            Log.e("RouterTAG", "非法访问异常");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            Log.e("RouterTAG", "调用目标异常");
        }
        return null;
    }

}
