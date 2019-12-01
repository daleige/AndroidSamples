package com.cyq.mvpdemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class HandlerTestActivity extends AppCompatActivity {
    //写法1
    private Handler mHandler1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {

        }
    };


    private Handler mHandler2 = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            return false;
        }
    });


//    private MyHandler mHandler = new MyHandler(this);
//
//    private static class MyHandler extends Handler {
//        private final WeakReference<HandlerTestActivity> mActivity;
//
//        public MyHandler(HandlerTestActivity mActivity) {
//            this.mActivity = new WeakReference<>(mActivity);
//        }
//
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            HandlerTestActivity activity = mActivity.get();
//            if (activity != null) {
//                activity.startActivity(new Intent(activity, LoginActivity.class));
//            }
//        }
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_test);
        test();
    }

    private void test() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = new Message();
                //模拟网络请求等耗时操作，休眠3秒的时候双击返回键销毁当前activity
                SystemClock.sleep(3000);
                msg.what = 1;
                if (mHandler1 != null) {
                    mHandler1.sendMessage(msg);
                }
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler1 != null) {
            mHandler1.removeCallbacksAndMessages(null);
            mHandler1 = null;
        }
        Log.d("test", "activity 执行 onDestroy");
    }
}
