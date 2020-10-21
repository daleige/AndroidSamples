package com.cyq.jetpack;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author : ChenYangQi
 * date   : 2020/10/21 16:44M
 * desc   :
 */
class ThreadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        thread();
    }

    /**
     * Thread方式实现
     */
    void thread() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                System.out.println("Thread started!");
            }
        };
        thread.start();
    }

    /**
     * Runnable方式
     */
    void runnable() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread with Runnable started!");
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    /**
     * threadFactory实现
     */
    void threadFrctory() {
        ThreadFactory factory = new ThreadFactory() {
            int count = 0;

            @Override
            public Thread newThread(Runnable r) {
                count++;
                return new Thread(r, "my_thead_" + count);
            }
        };
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " started");
            }
        };

        Thread thread = factory.newThread(runnable);
        thread.start();
        Thread thread1 = factory.newThread(runnable);
        thread1.start();
    }

    /**
     * 线程池方式
     */
    void executor() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("executor run thread started!");
            }
        };

        //1. newCachedThreadPool初始状态没有线程，用的时候创建线程，线程用完存一段时间再回收线程，线程数量没有上限，适用于大多数情况
        Executor executor = Executors.newCachedThreadPool();
        //2. newSingleThreadExecutor单线程的线程池 超过一个任务就要排队
        Executor executor1 = Executors.newSingleThreadExecutor();
        //3. newFixedThreadPool创建固定线程数量的线程池，适用于一次性的批量操作，比如要对一万张图片进行鉴黄，鉴定完毕回收线程
        Executor executor2 = Executors.newFixedThreadPool(20);
        Runnable processImageRunnable = new Runnable() {
            @Override
            public void run() {
                //TODO 处理图片鉴黄
            }
        };
        for (int i = 0; i < 10000; i++) {
            executor2.execute(processImageRunnable);
        }
        executor2.shudown();

        //4. newScheduledThreadPool
        Executor executor3 = Executors.newScheduledThreadPool(10);

        BlockingDeque<Runnable> queue = new LinkedBlockingDeque<>(10000);
        Executor customThreadPool = new ThreadPoolExecutor(5, 100, 5, TimeUnit.SECONDS, queue)

        executor.execute(runnable);
        executor.execute(runnable);
        executor.execute(runnable);
    }

    void callable() {

    }

}
