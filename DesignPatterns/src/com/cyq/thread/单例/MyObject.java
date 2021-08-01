package src.com.cyq.thread.单例;

import src.com.cyq.thread.condition.MyService;

public class MyObject {
    private volatile static MyObject myObject;

    private void MyService() {
    }

    public static MyObject getInstance() {
        try {
            if (myObject == null) {
                Thread.sleep(2000);
                synchronized (MyObject.class) {
                    if (myObject == null) {
                        myObject = new MyObject();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return myObject;
    }
}
