package com.cyq.lib_network.callback;


import com.cyq.lib_network.HttpError;

import retrofit2.Call;
import retrofit2.Response;

/**
 * @author chenyq113@midea.com
 * @describe xxx
 * @time 2021/2/1 14:11
 */
public abstract class BodyCallback<T> implements MyCallback<T> {

    @Override
    public final void onResponse(Call<T> call, Response<T> response) {
        if (response.body() != null) {
            onSuccess(call, response.body());
        } else {
            HttpError httpError = new HttpError(-1, "请求失败！");
            onError(call, httpError);
        }
    }

    @Override
    public final void onFailure(Call<T> call, Throwable t) {
        HttpError httpError = new HttpError(-1, "网络异常！");
        onError(call, httpError);
    }

    protected abstract void onError(Call<T> call, HttpError error);

    protected abstract void onSuccess(Call<T> call, T t);

}
