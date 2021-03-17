package src.com.cyq.test;

public class Test {
    public static void main(String[] args) {

        TestTask mTask = new TestTask();
        mTask.setCallback(new TestTask.Callback() {
            @Override
            public void onFinish(boolean result) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new Thread(mTask).start();
            }
        });
        new Thread(mTask).start();
    }
}
