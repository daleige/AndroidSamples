package src.com.cyq.thread.单例;

public class StaticInstance {

    private StaticInstance() {
    }

    private static class StaticInstanceHandler {
        private static StaticInstance instance = new StaticInstance();
    }

    public static StaticInstance getInstance() {
        return StaticInstanceHandler.instance;
    }
}
