package com.cyq.design.单例;

/**
 * 懒汉式 - 双重检查
 * 优点：线程安全，延迟加载，效率高
 * 开发中推荐使用这种方案
 */
public class Singleton4 {
    private static volatile Singleton4 instance;

    private Singleton4() {
    }

    public static Singleton4 getInstance() {
        if (instance == null) {
            synchronized (Singleton4.class) {
                if (instance == null) {
                    instance = new Singleton4();
                }
            }
        }
        return instance;
    }
}
