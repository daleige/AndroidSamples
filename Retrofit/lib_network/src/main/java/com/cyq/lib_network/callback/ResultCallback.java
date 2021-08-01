package com.cyq.lib_network.callback;

import android.util.Log;

import com.cyq.lib_network.BaseResult;
import com.cyq.lib_network.HttpError;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Response;

/**
 * @author chenyq113@midea.com
 * @describe xxx
 * @time 2021/2/1 16:21
 */
public abstract class ResultCallback<T> implements MyCallback<BaseResult<T>> {

    public ResultCallback() {
        onStart();
    }

    @Override
    public void onResponse(Call<BaseResult<T>> call, Response<BaseResult<T>> response) {
        onCompleted(call);
        if (response.body() != null) {
            BaseResult<T> baseResult = response.body();
            T t = baseResult.getData();
            if (t != null) {
                onSuccess(call, t);
            }
        }
    }

    @Override
    public void onFailure(Call<BaseResult<T>> call, Throwable t) {
        onCompleted(call);
        onError(call, new HttpError(-1, "网络异常！"));
    }

    protected abstract void onError(Call<BaseResult<T>> call, HttpError error);

    protected abstract void onSuccess(Call<BaseResult<T>> call, T t);
}
