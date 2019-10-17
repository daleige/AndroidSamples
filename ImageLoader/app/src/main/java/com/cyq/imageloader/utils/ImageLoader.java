package com.cyq.imageloader.utils;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.util.LruCache;
import android.widget.ImageView;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Time: 2019-10-17 23:34
 * Author: ChenYangQi
 * Description:
 */
public class ImageLoader {

    private static ImageLoader mInstance;
    //图片缓存核心对象
    private LruCache<String, Bitmap> mLruCache;
    //线程池
    private ExecutorService mThreadPool;
    //默认线程数
    private static final int DEFAULT_THREAD_COUNT = 1;
    //队列调度方式
    private Type mType = Type.LIFO;
    private LinkedList<Runnable> mTaskQueue;
    //后台轮询线程
    private Thread mPoolThread;
    private Handler mPoolThreadHandler;
    //UI线程中的Handler
    private Handler mUIHandler;

    public enum Type {
        FIFO, LIFO
    }

    private ImageLoader(int threadCount, Type type) {
        init(threadCount, type);
    }

    public static ImageLoader getInstance() {
        if (mInstance == null) {
            synchronized (ImageLoader.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoader(DEFAULT_THREAD_COUNT, Type.LIFO);
                }
            }
        }
        return mInstance;
    }

    /**
     * 初始化
     *
     * @param threadCount
     * @param type
     */
    private void init(int threadCount, Type type) {
        mPoolThread = new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                mPoolThreadHandler = new Handler() {
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        //线程池去取出一个任务进行轮询

                    }
                };
                Looper.loop();
            }
        };

        mPoolThread.start();

        //获取我们应用的最大可使用内存
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheMemory = maxMemory / 8;

        //初始化缓存对象
        mLruCache = new LruCache<String, Bitmap>(cacheMemory) {
            @Override
            protected int sizeOf(@NonNull String key, @NonNull Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };

        //初始化线程池
        mThreadPool = Executors.newFixedThreadPool(threadCount);
        mTaskQueue = new LinkedList<>();
        mType = type;
    }

    /**
     * 根据path为imageview设置图片
     *
     * @param path
     * @param imageView
     */
    public void loadImage(String path, ImageView imageView) {
        imageView.setTag(path);
        if (mUIHandler == null) {
            mUIHandler = new Handler() {
                @Override
                public void handleMessage(@NonNull Message msg) {
                    //获取得到的图片，为imageview设置图片
                    ImgBeanHolder holder = (ImgBeanHolder) msg.obj;
                    Bitmap bm = holder.bitmap;
                    ImageView imageView = holder.imageView;
                    String path = holder.path;
                    //将path与getTag存储路劲进行比较
                    if (imageView.getTag().toString().equals(path)) {
                        imageView.setImageBitmap(bm);
                    }
                }
            };
        }

        Bitmap bm = getBitmapFromLruCache(path);

        if (bm != null) {
            Message message = Message.obtain();
            ImgBeanHolder holder = new ImgBeanHolder();
            holder.bitmap = bm;
            holder.imageView = imageView;
            holder.path = path;
            message.obj = holder;
            mUIHandler.sendMessage(message);
        } else {
            addTasks(new Runnable() {
                @Override
                public void run() {

                }
            });
        }
    }

    private void addTasks(Runnable runnable) {

    }

    //根据path在缓存中获取bitmap
    private Bitmap getBitmapFromLruCache(String key) {
        return mLruCache.get(key);
    }

    private class ImgBeanHolder {
        Bitmap bitmap;
        ImageView imageView;
        String path;
    }
}
