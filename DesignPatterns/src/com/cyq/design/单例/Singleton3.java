package com.cyq.design.单例;

/**
 * 懒汉式 线程安全
 * 优点：加了同步，线程安全
 * 缺点：效率太低，开发中不推荐使用这种写法
 */
public class Singleton3 {

    private static Singleton3 INSTANCE;

    private Singleton3() {
    }

    public static synchronized Singleton3 getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new Singleton3();
        }
        return INSTANCE;
    }

    //方式二 线程不安全
    static class  Singleton7{
        private static Singleton7 instance;

        private Singleton7(){}

        public static Singleton7 getInstance() {
            if(instance==null){
                synchronized (Singleton7.class){
                    instance=new Singleton7();
                }
            }
            return instance;
        }
    }
}
