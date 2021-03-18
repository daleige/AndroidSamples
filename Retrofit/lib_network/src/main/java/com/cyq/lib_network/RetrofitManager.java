package com.cyq.lib_network;

import android.content.Context;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * @author ChenYangQi
 * @describe xxx
 * @time 2021/2/1 11:35
 */
public class RetrofitManager {

    private static volatile RetrofitManager instance;
    private Retrofit mRetrofit;
    private Context mContext;
    private Executor mExecutor;

    private RetrofitManager() {
        mRetrofit = init();
    }

    public static RetrofitManager getInstance() {
        if (instance == null) {
            synchronized (RetrofitManager.class) {
                if (instance == null) {
                    instance = new RetrofitManager();
                }
            }
        }
        return instance;
    }

    public void initClient(Context context) {
        mContext = context;
    }

    private Retrofit init() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //.addInterceptor(loggingInterceptor)
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        mRetrofit = new Retrofit.Builder()
                .baseUrl(AppConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .callbackExecutor(executorService)
                .client(okHttpClient)
                .build();
        return mRetrofit;
    }

    public <T> T setRequest(Class<T> clz) {
        return mRetrofit.create(clz);
    }

    public Context getContext() {
        return mContext;
    }
}
