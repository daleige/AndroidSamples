package com.cyq.lib_network.callback;

import android.text.TextUtils;
import android.util.Log;

import com.cyq.lib_network.HttpError;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Response;

/**
 * @author chenyq113@midea.com
 * @describe xxx
 * @time 2021/2/1 17:25
 */
public abstract class StringCallback implements MyCallback<String> {
    public StringCallback() {
        onStart();
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        Log.i("test","---------"+new Gson().toJson(response));
        onCompleted(call);
        if (!TextUtils.isEmpty(response.body())) {
            onSuccess(call, response.body());
        } else {
            onError(call, new HttpError(-1, "网络请求出错！"));
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        onCompleted(call);
        onError(call, new HttpError(-2, "网络请求出错！"));
    }

    protected abstract void onError(Call<String> call, HttpError error);

    protected abstract void onSuccess(Call<String> call, String result);
}
