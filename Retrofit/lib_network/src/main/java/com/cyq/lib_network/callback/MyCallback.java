package com.cyq.lib_network.callback;

import androidx.annotation.UiThread;

import retrofit2.Call;

/**
 * @author chenyq113@midea.com
 * @describe xxx
 * @time 2021/2/1 15:07
 */
public interface MyCallback<T> extends retrofit2.Callback<T> {

    @UiThread
    void onStart();

    @UiThread
    void onCompleted(Call<T> call);
}
