package src.com.cyq.design.工厂模式.工厂单例;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Singleton singleton = SingletonFactory.getSingleton();
                    System.out.println(singleton.hashCode());
                }
            }).start();
        }
    }
}
