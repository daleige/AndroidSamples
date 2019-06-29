package com.cyq.mvcdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.cyq.mvcdemo.bean.ImageBean;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageDownloader {
    static final int SUCCESS = 200;
    static final int ERROR = 401;

    /**
     * 下载图片
     *
     * @param callback
     * @param imageBean
     */
    public static void down(Callback callback, ImageBean imageBean) {
        new Thread(new Downloader(callback, imageBean)).start();
    }

    static final class Downloader implements Runnable {
        private final Callback callback;
        private final ImageBean imageBean;

        public Downloader(Callback callback, ImageBean imageBean) {
            this.callback = callback;
            this.imageBean = imageBean;
        }

        @Override
        public void run() {
            try {
                URL url = new URL(imageBean.getRequestPath());
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("GET");
                if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = httpURLConnection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    showUI(SUCCESS, bitmap);
                } else {
                    showUI(ERROR, null);
                }
            } catch (Exception e) {
                e.printStackTrace();
                showUI(ERROR, null);
            }
        }

        private void showUI(int resultCode, Bitmap bitmap) {
            if (callback != null) {
                imageBean.setBitmap(bitmap);
                callback.callback(resultCode, imageBean);
            }
        }
    }
}
