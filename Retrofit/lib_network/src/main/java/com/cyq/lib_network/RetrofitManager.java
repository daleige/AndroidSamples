package com.cyq.lib_network;

import android.content.Context;

import com.cyq.lib_network.dialog.DefaultHttpDialog;
import com.cyq.lib_network.dialog.IHttpLoadingDialog;

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
    private IHttpLoadingDialog mDialog;

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

    public RetrofitManager initClient(Context context) {
        mContext = context;
        if (mDialog == null) {
            //setLoadingDialog(new DefaultHttpDialog(mContext));
        }
        return this;
    }

    public RetrofitManager setLoadingDialog(IHttpLoadingDialog iHttpLoadingDialog) {
        this.mDialog = iHttpLoadingDialog;
        return this;
    }

    public IHttpLoadingDialog getLoadingDialog() {
        return mDialog;
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
