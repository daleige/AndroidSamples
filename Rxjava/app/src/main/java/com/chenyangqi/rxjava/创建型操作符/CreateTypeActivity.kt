package com.chenyangqi.rxjava.创建型操作符

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.chenyangqi.rxjava.R
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_create_type.*

class CreateTypeActivity : AppCompatActivity() {
    companion object {
        const val TAG = "CreateTypeActivity_TAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_type)

        btnCreate.setOnClickListener { createOption() }
        btnJust.setOnClickListener { justOption() }
        btnEmpty.setOnClickListener { emptyOption() }
        btnFormArray.setOnClickListener { formArrayOption() }
        btnRange.setOnClickListener { rangeOption() }
    }

    /**
     * createa操作符
     */
    private fun createOption() {
        Observable.create(object : ObservableOnSubscribe<String> {
            override fun subscribe(e: ObservableEmitter<String>?) {
                e?.onNext("create onNext...")
                Log.d(TAG, "Create操作符 onNext()")
            }
        }).subscribe(object : Observer<String> {
            override fun onSubscribe(d: Disposable?) {
                Log.d(TAG, "Create操作符 onSubscribe()")
            }

            override fun onNext(t: String?) {
                Log.d(TAG, "Create操作符 onNext()=$t")
            }

            override fun onError(e: Throwable?) {
                Log.d(TAG, "Create操作符 onError()")
            }

            override fun onComplete() {
                Log.d(TAG, "Create操作符 onComplete()")
            }
        })
    }

    /**
     * empty操作符，主要用于切换到子线程做耗时操作，不用跟新UI的场景
     */
    private fun emptyOption() {
        Observable.empty<Any>()
            .observeOn(Schedulers.io())
            .subscribe(object : Observer<Any> {
                override fun onSubscribe(d: Disposable?) {
                    Log.d(TAG, "empty操作符 onSubscribe() threadName=" + Thread.currentThread().name)
                }

                override fun onNext(t: Any?) {
                    Log.d(TAG, "empty操作符 onNext()")
                }

                override fun onError(e: Throwable?) {
                    Log.d(TAG, "empty操作符 onError()")
                }

                override fun onComplete() {
                    Log.d(TAG, "empty操作符 onComplete() threadName=" + Thread.currentThread().name)
                }
            })

        //简化版
        val emptyOption = object : Consumer<Any> {
            override fun accept(t: Any) {
                Log.d(TAG, "简化版empty$t")
            }
        }
        emptyOption.accept("111")
    }

    /**
     * just操作符
     */
    private fun justOption() {
        Observable.just("张三", "李四")
            .subscribe(object : Observer<String> {
                override fun onSubscribe(d: Disposable?) {
                    Log.d(TAG, "just操作符 onSubscribe()")
                }

                override fun onNext(t: String?) {
                    Log.d(TAG, "just操作符 onSubscribe()=$t")
                }

                override fun onError(e: Throwable?) {
                    Log.d(TAG, "just操作符 onSubscribe()")
                }

                override fun onComplete() {
                    Log.d(TAG, "just操作符 onSubscribe()")
                }
            })
    }

    /**
     * formArray操作符
     */
    private fun formArrayOption() {
        Observable.fromArray("aa", "bb", "cc")
            .subscribe(object : Observer<String> {
                override fun onSubscribe(d: Disposable?) {
                    Log.d(TAG, "formArray操作符 onSubscribe()")
                }

                override fun onNext(t: String?) {
                    Log.d(TAG, "formArray操作符 onNext()=$t")
                }

                override fun onError(e: Throwable?) {
                    Log.d(TAG, "formArray操作符 onError()")
                }

                override fun onComplete() {
                    Log.d(TAG, "formArray操作符 onComplete()")
                }
            })
    }

    /**
     * range操作符，start:开始数据，count：递增个数
     */
    private fun rangeOption() {
        Observable.range(10, 5)
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable?) {
                    Log.d(TAG, "rangeArray操作符 onSubscribe()")
                }

                override fun onNext(t: Int?) {
                    Log.d(TAG, "rangeArray操作符 onNext()=$t")
                }

                override fun onError(e: Throwable?) {
                    Log.d(TAG, "rangeArray操作符 onError()")
                }

                override fun onComplete() {
                    Log.d(TAG, "rangeArray操作符 onComplete()")
                }
            })
    }
}