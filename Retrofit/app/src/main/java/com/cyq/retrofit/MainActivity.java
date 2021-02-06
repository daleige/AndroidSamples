package com.cyq.retrofit;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cyq.lib_network.BaseResult;
import com.cyq.lib_network.HttpError;
import com.cyq.lib_network.RetrofitManager;
import com.cyq.lib_network.callback.BaseResultCallback;
import com.cyq.lib_network.callback.ResultCallback;
import com.cyq.lib_network.callback.StringCallback;
import com.cyq.retrofit.bean.DeviceInfo;
import com.cyq.retrofit.bean.Person;
import com.cyq.retrofit.bean.PersonBean;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

import static com.cyq.retrofit.R.id.btnPostFormat1;
import static com.cyq.retrofit.R.id.btnPostFormat2;
import static com.cyq.retrofit.R.id.btnDownLoad;

/**
 * @author chenyq113@midea.com
 * @describe xxx
 * @time 2021/2/1 14:18
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private Button button1, button2, btnDownLoad, btnGet, btnPostJson;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(btnPostFormat1);
        button2 = findViewById(btnPostFormat2);
        btnDownLoad = findViewById(R.id.btnDownLoad);
        btnGet = findViewById(R.id.btnGet);
        btnGet.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        btnDownLoad.setOnClickListener(this);

        btnPostJson = findViewById(R.id.btnPostJson);
        btnPostJson.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btnPostFormat1) {
            postBodyCallback();
        } else if (v.getId() == btnPostFormat2) {
            postDefaultCallback();
        } else if (v.getId() == R.id.btnDownLoad) {

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
                .setRequest(RequestAPI.class)
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
                .setRequest(RequestAPI.class)
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
                .setRequest(RequestAPI.class)
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
                .setRequest(RequestAPI.class)
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

}
