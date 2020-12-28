package src.com.cyq.design.单例;

/**
 * 懒汉式-线程不安全 单例模式
 * 优点：做到了懒加载
 * 缺点：多线程使用的时候会产生多个实例，实际开发中不要使用这种方案
 */
public class SingLeton2 {
    private static SingLeton2 INSTANCE;

    private SingLeton2() {
    }

    public static SingLeton2 getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SingLeton2();
        }
        return INSTANCE;
    }

}
