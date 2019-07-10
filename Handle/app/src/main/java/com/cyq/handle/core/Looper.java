package com.cyq.handle.core;


public class Looper {
    public MessageQueue mQueue;
    static final ThreadLocal<Looper> sThreadLocal = new ThreadLocal<>();

    private Looper() {
        mQueue = new MessageQueue();
    }

    public static void prepare() {
        if (sThreadLocal.get() != null) {
            throw new RuntimeException("Only one Looper may be create per thread");
        }
        //
        sThreadLocal.set(new Looper());
    }

    public static Looper myLooper() {
        return sThreadLocal.get();
    }

    /**
     * 轮询，提取消息
     */
    public static void loop() {
        //从全局ThreadLocalMap中获取唯一：Looper对象
        Looper me = myLooper();
        final MessageQueue queue = me.mQueue;

        Message resultMessage;
        while (true) {
            Message msg = queue.next();
            if (msg != null) {
                msg.target.dispatchMessage(msg);
            }
        }
    }
}
