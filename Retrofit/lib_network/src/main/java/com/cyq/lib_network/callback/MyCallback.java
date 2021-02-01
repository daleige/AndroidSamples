package com.cyq.lib_network.callback;

import retrofit2.Call;

/**
 * @author chenyq113@midea.com
 * @describe xxx
 * @time 2021/2/1 15:07
 */
public interface MyCallback<T> extends retrofit2.Callback<T> {

    void onStart(Call<T> call);

    void onCompleted(Call<T> call);
}
