package com.chenyangqi.aidl.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    MyService.MyBinder binder = null;
    IMsgManager mIMsgManager;
    ServiceConnection mConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                binder = (MyService.MyBinder) iBinder;
                mIMsgManager = IMsgManager.Stub.asInterface(iBinder);

                try {
                    mIMsgManager.asBinder().linkToDeath(mDeathRecipient, 0);
                    mIMsgManager.registerReceiveListener(mReceiveMsgListener);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };

        //注意Activity和Service是同一进程才能使用Intent通信
        Intent intent = new Intent(MainActivity.this, MyService.class);
        bindService(intent, mConnection, BIND_AUTO_CREATE);//开启服务

        new Handler().postDelayed(() -> {
            for (int i = 0; i < 10; i++) {
                if (binder != null) {
                    binder.sendMsg(new Msg("发送的信息----" + i, System.currentTimeMillis()));
                }
            }
        }, 3999);
    }

    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {
        //当承载IBinder的进程消失时接收回调的接口
        @Override
        public void binderDied() {
            if (null == mIMsgManager) {
                return;
            }
            mIMsgManager.asBinder().unlinkToDeath(mDeathRecipient, 0);
            mIMsgManager = null;
            //断线重来逻辑
        }
    };


    private final IReceiveMsgListener mReceiveMsgListener = new IReceiveMsgListener.Stub() {

        @Override
        public void onReceive(Msg msg) {
            msg.setTime(System.currentTimeMillis());
            //接收到的数据
            Log.d("test", "接收到数据=" + msg);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除注册
        if (null != mIMsgManager && mIMsgManager.asBinder().isBinderAlive()) {
            try {
                mIMsgManager.unregisterReceiveListener(mReceiveMsgListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        //解除绑定服务
        unbindService(mConnection);
        super.onDestroy();
    }
}