package com.cyq.handle;

import com.cyq.handle.core.Handle;
import com.cyq.handle.core.Looper;
import com.cyq.handle.core.Message;

import org.junit.Test;

public class ActivityThread {
    @Test
    public void main() {
        //创建全局唯一的，主线程Looper对象，以及MessageQueue消息队列对象
        Looper.prepare();
        //模拟Activity创建Handler对象
        final Handle handle = new Handle() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                System.out.println(msg.obj.toString());
            }
        };
        //消费消息，回调方法（接口方法）

        //子线程发送handle消息
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message=new Message();
                message.obj="hello android";
               handle.sendMessage(message);
            }
        }).start();

        //轮训，取出消息
        Looper.loop();

    }
}
