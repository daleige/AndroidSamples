package com.example.network.http

import android.content.Context
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

abstract class MyObserver<T>(context: Context) : Observer<T> {
    private var mContext: Context? = context

    override fun onSubscribe(d: Disposable) {
        //TODO 如果无网络等情况，可以直接结束
        if (false) {
            onFail(ApiException(9999, "当前无网络，请检查网络设置后再试！"))
            onComplete()
        }
    }

    override fun onNext(t: T) {
        onSuccess(t)
    }

    override fun onError(e: Throwable) {
        onFail(ApiException(-1, e.message.toString()))
    }

    override fun onComplete() {
        release()
    }

    private fun release() {
        mContext = null
    }

    abstract fun onSuccess(t: T)

    abstract fun onFail(apiException: ApiException)
}