package com.cyq.retrofit;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.cyq.lib_network.BaseResult;
import com.cyq.lib_network.HttpError;
import com.cyq.lib_network.RetrofitManager;
import com.cyq.lib_network.callback.BaseResultCallback;
import com.cyq.lib_network.callback.DownloadCallback;
import com.cyq.lib_network.callback.ResultCallback;
import com.cyq.retrofit.bean.DeviceInfo;
import com.cyq.retrofit.bean.Person;
import com.cyq.retrofit.bean.PersonBean;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

import static com.cyq.retrofit.R.id.btnPostFormat1;
import static com.cyq.retrofit.R.id.btnPostFormat2;
import static com.cyq.retrofit.R.id.fill;
import static com.cyq.retrofit.R.id.image;

/**
 * @author chenyq113@midea.com
 * @describe xxx
 * @time 2021/2/1 14:18
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private static final String[] permission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};
    private ProgressBar progressBar;
    private TextView tvProgress;
    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(btnPostFormat1);
        Button button2 = findViewById(btnPostFormat2);
        Button btnDownLoad = findViewById(R.id.btnDownLoad);
        progressBar = findViewById(R.id.progress);
        tvProgress = findViewById(R.id.tvProgress);
        Button btnGet = findViewById(R.id.btnGet);
        imageView = findViewById(R.id.image);
        btnGet.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        btnDownLoad.setOnClickListener(this);
        Button btnPostJson = findViewById(R.id.btnPostJson);
        btnPostJson.setOnClickListener(this);
        //文件读写权限
        ActivityCompat.requestPermissions(this, permission, 100);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btnPostFormat1) {
            postBodyCallback();
        } else if (v.getId() == btnPostFormat2) {
            postDefaultCallback();
        } else if (v.getId() == R.id.btnDownLoad) {
            downloadFile();
        } else if (v.getId() == R.id.btnGet) {
            getRequest();
        } else if (v.getId() == R.id.btnPostJson) {
            postRequestJson();
        }
    }

    private void postRequestJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", "SDFS7656SDF");
        jsonObject.addProperty("name", "特斯拉 Mode 3");
        Log.d(TAG, "入参：" + jsonObject.toString());
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        RetrofitManager.getInstance()
                .setRequest(ApiService.class)
                .postJson(requestBody)
                .enqueue(new BaseResultCallback<DeviceInfo>() {
                    @Override
                    protected void onError(Call<DeviceInfo> call, HttpError error) {
                        Log.d(TAG, "请求出错");
                    }

                    @Override
                    protected void onSuccess(Call<DeviceInfo> call, DeviceInfo deviceInfo) {
                        Log.d(TAG, "请求成功：" + deviceInfo.toString());
                    }

                    @Override
                    public void onStart() {
                        Log.d(TAG, "开始请求");
                    }

                    @Override
                    public void onCompleted(Call<DeviceInfo> call) {
                        Log.d(TAG, "完成请求");
                    }
                });
    }

    private void getRequest() {
        RetrofitManager.getInstance()
                .setRequest(ApiService.class)
                .getDeviceInfo("123123123")
                .enqueue(new BaseResultCallback<DeviceInfo>() {
                    @Override
                    protected void onError(Call<DeviceInfo> call, HttpError error) {
                        Log.d(TAG, "get请求出错");
                    }

                    @Override
                    protected void onSuccess(Call<DeviceInfo> call, DeviceInfo deviceInfo) {
                        Log.d(TAG, "get请求成功：" + deviceInfo.toString());
                    }

                    @Override
                    public void onStart() {
                        Log.d(TAG, "开始get请求");
                    }

                    @Override
                    public void onCompleted(Call<DeviceInfo> call) {
                        Log.d(TAG, "完成get请求");
                    }
                });
    }

    private void postBodyCallback() {
        RetrofitManager.getInstance()
                .setRequest(ApiService.class)
                .getPersonInfo(1189, "周旭旭")
                .enqueue(new BaseResultCallback<PersonBean>() {

                    @Override
                    public void onStart() {
                        Log.i(TAG, "onStart().....");
                    }

                    @Override
                    public void onCompleted(Call<PersonBean> call) {
                        Log.i(TAG, "onCompleted().....");
                    }

                    @Override
                    protected void onError(Call<PersonBean> call, HttpError error) {
                        Log.i(TAG, "onError().....");
                    }

                    @Override
                    protected void onSuccess(Call<PersonBean> call, PersonBean personBean) {
                        Log.i(TAG, "onSuccess().....");
                        Log.i(TAG, "请求成功：" + new Gson().toJson(personBean));

                    }
                });
    }

    private void postDefaultCallback() {
        RetrofitManager.getInstance()
                .setRequest(ApiService.class)
                .getPersonInfo2(134, "张三")
                .enqueue(new ResultCallback<Person>() {
                    @Override
                    protected void onError(Call<BaseResult<Person>> call, HttpError error) {
                        Log.i(TAG, "onError().....");
                    }

                    @Override
                    protected void onSuccess(Call<BaseResult<Person>> call, Person person) {
                        Log.i(TAG, "onSuccess().....");
                        Log.i("test", "请求结果：" + new Gson().toJson(person));
                    }

                    @Override
                    public void onStart() {
                        Log.i(TAG, "onStart().....");
                    }

                    @Override
                    public void onCompleted(Call<BaseResult<Person>> call) {
                        Log.i(TAG, "onCompleted().....");
                    }
                });
    }

    private void downloadFile() {
        Log.i(TAG, "开始下载----");
        RetrofitManager.getInstance()
                .setRequest(ApiService.class)
                .downloadFile("https://imtt.dd.qq.com/16891/apk/0C597750D60780F490BECEC13A0243BA.apk?fsname=com.tencent.mobileqq_8.5.5_1630.apk")
                .enqueue(new DownloadCallback<ResponseBody>("com.tencent.mobileqq_8.5.5_1630.apk") {
                    @Override
                    public void onProgress(float progress) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.i(TAG, "下载进度：" + progress);
                                final int currentProgress = (int) (100 * progress);
                                progressBar.setProgress(currentProgress);
                                tvProgress.setText(currentProgress + "%");
                            }
                        });
                    }

                    @Override
                    protected void onSuccess(File file) {
                        Log.i(TAG, "下载成功：" + file.getAbsolutePath());
                    }

                    @Override
                    protected void onError(Call<ResponseBody> call, HttpError error) {
                        Log.i(TAG, "文件下载失败！");
                    }
                });
    }


}
