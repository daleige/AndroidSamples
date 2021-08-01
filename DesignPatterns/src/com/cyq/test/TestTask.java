package src.com.cyq.test;

public class TestTask implements Runnable {
    @Override
    public void run() {
        System.out.println("0000000---------");
        if(mCallback!=null){
            mCallback.onFinish(true);
        }
    }

    private Callback mCallback;

    public void setCallback(Callback mCallback) {
        this.mCallback = mCallback;
    }

    public interface Callback{
        void onFinish(boolean result);
    }
}
