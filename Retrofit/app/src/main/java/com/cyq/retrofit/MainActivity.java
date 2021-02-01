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
import com.cyq.retrofit.bean.Person;
import com.cyq.retrofit.bean.PersonBean;
import com.google.gson.Gson;

import retrofit2.Call;

import static com.cyq.retrofit.R.id.btnPostFormat1;
import static com.cyq.retrofit.R.id.btnPostFormat2;
import static com.cyq.retrofit.R.id.btnPostFormat3;

/**
 * @author chenyq113@midea.com
 * @describe xxx
 * @time 2021/2/1 14:18
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private Button button1, button2, button3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(btnPostFormat1);
        button2 = findViewById(btnPostFormat2);
        button3 = findViewById(btnPostFormat3);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == btnPostFormat1) {
            postBodyCallback();
        } else if (v.getId() == btnPostFormat2) {
            postDefaultCallback();
        } else if (v.getId() == btnPostFormat3) {
            postStringCallback();
        }
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

    private void postStringCallback() {

    }


}
