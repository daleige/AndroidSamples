package src.com.cyq.design.单例;

/**
 * 静态内部类 单例
 */
public class Singleton5 {
    private Singleton5() {
    }

    private static class Singleton5Instance {
        private static final Singleton5 INSTANCE = new Singleton5();
    }

    public static Singleton5 getInstance() {
        return Singleton5Instance.INSTANCE;
    }
}
