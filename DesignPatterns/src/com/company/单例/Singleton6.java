package src.com.company.单例;

/**
 * 枚举方式创建单例
 * <<Effective Java>>推荐使用这种方式创建单例
 */
public enum Singleton6 {
    INSTANCE;

    public void doSomething() {
        System.out.println("枚举创建");
    }
}
