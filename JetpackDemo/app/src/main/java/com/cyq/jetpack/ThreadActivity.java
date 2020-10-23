package com.cyq.jetpack;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author : ChenYangQi
 * date   : 2020/10/21 16:44M
 * desc   :
 */
public class ThreadActivity extends AppCompatActivity {
    private volatile String a = "0";
    private AtomicInteger b = new AtomicInteger();
    private Lock lock = new ReentrantLock();
    private ReentrantReadWriteLock lock2 = new ReentrantReadWriteLock();
    //读写锁 读锁的时候其他线程也可以读，写的时候其他线程不能写
    private ReentrantReadWriteLock.ReadLock lock3 = lock2.readLock();
    private ReentrantReadWriteLock.WriteLock lock4 = lock2.writeLock();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        //thread();
        callable();



    }

    private void reset(String str) {
        lock.lock();
        try {
            a = str;
        } finally {
            lock.unlock();
        }
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
            AtomicInteger count = new AtomicInteger();

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "my_thead_" + count.incrementAndGet());
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
        ExecutorService executor2 = Executors.newFixedThreadPool(20);
        Runnable processImageRunnable = new Runnable() {
            @Override
            public void run() {
                //TODO 处理图片鉴黄
            }
        };
        for (int i = 0; i < 10000; i++) {
            executor2.execute(processImageRunnable);
        }
        executor2.shutdown();

        //4. newScheduledThreadPool 延时调度周期性执行的线程池
        Executor executor3 = Executors.newScheduledThreadPool(10);

        BlockingDeque<Runnable> queue = new LinkedBlockingDeque<>(10000);
        Executor customThreadPool = new ThreadPoolExecutor(
                5,
                100,
                5,
                TimeUnit.SECONDS,
                queue);

        executor.execute(runnable);
        executor.execute(runnable);
        executor.execute(runnable);
    }


    void callable() {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "test data";
            }
        };
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(callable);
        try {
            String result = future.get();
            System.out.println("result:" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
