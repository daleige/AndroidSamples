package com.cyq.design.单例;

/**
 * 饿汉式方式实实现单例
 * 优点：类加载的时候完成实例化，避免了线程同步问题
 * 缺点：在类加载的时候就完成实例化，没有达到Lazy Loading的效果，如果该类未使用则造成了内存浪费
 */
public class Singleton1 {
    //方式1：通过静态变量方式创建
    private static final Singleton1 INSTANCE = new Singleton1();

    //方式2：通过静态代码块的方式，效果和方法1一样
//    private static final Singleton1 INSTANCE;
//
//    static {
//        INSTANCE = new Singleton1();
//    }

    /**
     * 私有的构造方法，防止产生多个对象
     */
    private Singleton1() {
    }

    /**
     * 通过该方法获得实例
     *
     * @return
     */
    public static Singleton1 getInstance() {
        return INSTANCE;
    }

    public void doSomething() {

    }
}
