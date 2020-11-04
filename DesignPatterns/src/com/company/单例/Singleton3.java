package src.com.company.单例;

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
}
