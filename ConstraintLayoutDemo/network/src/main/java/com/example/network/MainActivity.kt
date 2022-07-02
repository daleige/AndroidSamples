package com.example.network

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.network.bean.BannerBean
import com.example.network.bean.ChildBannerBean
import com.example.network.http.ApiException
import com.example.network.http.BaseRsp
import com.example.network.http.HttpUtil
import com.example.network.http.MyObserver
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var mTvResult: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mTvResult = findViewById(R.id.tv_result)

        findViewById<Button>(R.id.btn_get).setOnClickListener {
            HttpUtil.createService(RequestApi::class.java)
                .getBanner()
                .enqueue(object : Callback<BannerBean> {
                    override fun onResponse(
                        call: Call<BannerBean>,
                        response: Response<BannerBean>
                    ) {
                        Log.d(
                            "test",
                            "请求成功：${Gson().toJson(response.body())}  ${Thread.currentThread()}"
                        )
                        mTvResult?.text = Gson().toJson(response.body())
                    }

                    override fun onFailure(call: Call<BannerBean>, t: Throwable) {
                        Log.d("test", "请求失败：$call")
                    }
                })
        }

        findViewById<Button>(R.id.btn_rxjava_get).setOnClickListener {
//            HttpUtil.createService(RequestApi::class.java)
//                .getBanner2()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(object : MyObserver<BannerBean>(this) {
//                    override fun onSuccess(t: BannerBean) {
//                        Log.d("test", "onNext---->$t")
//                        mTvResult?.text = Gson().toJson(t)
//                    }
//
//                    override fun onFail(apiException: ApiException) {
//                        Log.d("test", "onError---->$apiException")
//                        apiException.printStackTrace()
//                    }
//                })
            HttpUtil.createService(RequestApi::class.java)
                .getBanner3()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    object : MyObserver<BaseRsp<List<ChildBannerBean>>>(this) {
                        override fun onSuccess(t: BaseRsp<List<ChildBannerBean>>) {
                            if (t.isSuccess()) {
                                Log.d("test", "请求成功：${t.data}")
                            } else {
                                Log.d("test", "请求失败：${t.errorMsg}")
                            }
                        }

                        override fun onFail(apiException: ApiException) {
                            Log.d("test", "onError---->$apiException")
                        }
                    }
                )
        }
    }
}