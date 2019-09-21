package com.cyq.customglide.resource;

import android.graphics.Bitmap;
import android.util.Log;

/**
 * Time: 2019-09-21 14:55
 * Author: ChenYangQi
 * Description:对Bitmap封装
 */
public class Value {
    private final String TAG = Value.class.getSimpleName();
    private Bitmap mBitmap;
    private ValueCallback callback;
    private String key;

    //单例模式
    private static Value value;

    public static Value getInstance() {
        if (null == value) {
            synchronized (Value.class) {
                if (null == value) {
                    value = new Value();
                }
            }
        }
        return value;
    }


    //使用计数
    private int count;

    /**
     * TODO 使用一次就加一
     */
    public void userAction() {
        if (mBitmap.isRecycled()) {
            Log.d(TAG, "userAction: 加一 count:" + count);
            return;
        }

        count++;
    }

    /**
     * TODO 使用完成（不使用）就减一
     */
    public void nonUseAction() {
        if (count-- <= 0 && callback != null) {
            //回调告诉外界，不再使用
            callback.valueNonUseListener(key, this);
        }
    }

    /**
     * Bitmap释放
     */
    public void recyclerBitmap() {
        if (count > 0) {
            Log.d(TAG, "recycleBitmap:引用计数大于0，证明还在使用中，不能去释放...");
        }

        if (mBitmap.isRecycled()) {//被释放了
            Log.d(TAG, "recyclerBitmap:bitmap.isRecycled() 已经被释放了");
            return;
        }

        mBitmap.recycle();
        value = null;
        System.gc();
    }

    public Bitmap getmBitmap() {
        return mBitmap;
    }

    public void setmBitmap(Bitmap mBitmap) {
        this.mBitmap = mBitmap;
    }

    public ValueCallback getCallback() {
        return callback;
    }

    public void setCallback(ValueCallback callback) {
        this.callback = callback;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
