package com.cyq.lib_network.callback;


import android.util.Log;

import com.cyq.lib_network.HttpError;
import com.cyq.lib_network.RetrofitManager;

import retrofit2.Call;
import retrofit2.Response;

/**
 * @author chenyq113@midea.com
 * @describe xxx
 * @time 2021/2/1 14:11
 */
public abstract class BaseResultCallback<T> implements MyCallback<T> {

    public BaseResultCallback() {
        onStart();
        Log.d("MainActivity", "onStart----------:" + Thread.currentThread());
        RetrofitManager.getInstance().getLoadingDialog().showDialog();
    }

    @Override
    public final void onResponse(Call<T> call, Response<T> response) {
        Log.d("MainActivity", "onResponse----------" + Thread.currentThread());
        RetrofitManager.getInstance().getLoadingDialog().dismissDialog();
        onCompleted(call);
        if (response.body() != null) {
            onSuccess(call, response.body());
        } else {
            HttpError httpError = new HttpError(-1, "请求失败！");
            onError(call, httpError);
        }
    }

    @Override
    public final void onFailure(Call<T> call, Throwable t) {
        Log.d("MainActivity", "onFailure----------" + Thread.currentThread());
        RetrofitManager.getInstance().getLoadingDialog().dismissDialog();
        onCompleted(call);
        HttpError httpError = new HttpError(-1, "网络异常！");
        onError(call, httpError);
    }

    protected abstract void onError(Call<T> call, HttpError error);

    protected abstract void onSuccess(Call<T> call, T t);

}
