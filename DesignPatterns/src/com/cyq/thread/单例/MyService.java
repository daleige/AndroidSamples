package src.com.cyq.thread.单例;

import java.util.Random;

public class MyService {

    public int number;

    private volatile static MyService instance;

    private MyService() {
        number = new Random().nextInt(100) + 1;
    }

    public static MyService getInstance() {
        if (instance == null) {
            synchronized (MyService.class) {
                if (instance == null) {
                    instance = new MyService();
                }
            }
        }
        return instance;
    }

    public static void reset(){
        instance=null;
    }
}
