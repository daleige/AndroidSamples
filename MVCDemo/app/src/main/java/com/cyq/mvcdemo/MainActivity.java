package com.cyq.mvcdemo;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.cyq.mvcdemo.bean.ImageBean;

public class MainActivity extends AppCompatActivity implements Callback {
    private ImageView imageView;
    private static final String path = "http://photocdn.sohu.com/20130416/Img372885486.jpg";
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case ImageDownloader.SUCCESS://请求成功
                    imageView.setImageBitmap((Bitmap) msg.obj);
                    break;
                case ImageDownloader.ERROR://请求失败
                    Toast.makeText(MainActivity.this, "下载失败", Toast.LENGTH_SHORT).show();
                    break;
            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.iv_picture);

        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(50000);
            }
        }).start();
    }

    /**
     * 点击事件，获取图片
     *
     * @param view
     */
    public void getImage(View view) {
        ImageBean imageBean = new ImageBean();
        imageBean.setRequestPath(path);
        ImageDownloader.down(this, imageBean);
    }

    @Override
    public void callback(int resultCode, ImageBean bitmap) {
        Message message = handler.obtainMessage(resultCode);
        message.obj = bitmap.getBitmap();
        handler.sendMessageDelayed(message, 500);
    }
}
