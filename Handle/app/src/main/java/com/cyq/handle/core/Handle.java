package com.cyq.handle.core;

public class Handle {
    private Looper mLooper;
    private MessageQueue mQueue;

    public Handle() {
        mLooper = Looper.myLooper();
        if (mLooper == null) {
            throw new RuntimeException("Cant't create handle inside thread");
        }
        mQueue = mLooper.mQueue;
    }


    public void sendMessage(Message message) {
        enqueueMessage(message);
    }

    private void enqueueMessage(Message message) {
        //赋值当前handle
        message.target=this;
        //使用mQueue,检消息放入
        mQueue.enqueueMessage(message);
    }

    public void dispatchMessage(Message msg){
        handleMessage(msg);
    }

    //给开发者提供的开放API，用于重写和回调监听
    public void handleMessage(Message msg) {


    }
}
