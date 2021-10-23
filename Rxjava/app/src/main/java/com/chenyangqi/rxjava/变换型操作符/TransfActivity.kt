package com.chenyangqi.rxjava.变换型操作符

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.chenyangqi.rxjava.R
import io.reactivex.*
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import io.reactivex.observables.GroupedObservable
import kotlinx.android.synthetic.main.activity_transf.*
import java.util.concurrent.TimeUnit

@SuppressLint("CheckResult")
class TransfActivity : AppCompatActivity() {
    companion object {
        const val TAG = "TransfActivity_TAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transf)

        btnMapOption.setOnClickListener { mapOption() }
        btnFlatMapOption.setOnClickListener { flatMapOption() }
        btnConcatMapOption.setOnClickListener { concatMapOption() }
        btnGroupByOption.setOnClickListener { groupByOption() }
        btnBufferOption.setOnClickListener { bufferOption() }
    }

    private fun mapOption() {
        Observable.just(1)
            .map(object : Function<Int, String> {
                override fun apply(t: Int): String {
                    Log.d(TAG, "map 操作符 Int 转 String")
                    return "result $t"
                }

            })
            .map(object : Function<String, Bitmap> {
                override fun apply(t: String): Bitmap {
                    Log.d(TAG, "map 操作符 String 转 Bitmap")
                    return Bitmap.createBitmap(10, 10, Bitmap.Config.ARGB_8888)
                }
            })
            .subscribe(object : Observer<Bitmap> {
                override fun onSubscribe(d: Disposable?) {
                    Log.d(TAG, "map onSubscribe()")
                }

                override fun onNext(t: Bitmap?) {
                    Log.d(
                        TAG, "map 操作符 onNext() bitmap size=${t?.width}"
                    )
                }

                override fun onError(e: Throwable?) {
                    Log.d(TAG, "map 操作符 onError()")
                }

                override fun onComplete() {
                    Log.d(TAG, "map 操作符 onComplete()")
                }
            })
    }

    private fun flatMapOption() {
        Observable.create(object : ObservableOnSubscribe<String> {
            override fun subscribe(e: ObservableEmitter<String>?) {
                e?.onNext("A ")
                e?.onNext("B ")
                e?.onNext("C ")
            }
        }).flatMap(object : Function<String, ObservableSource<String>> {
            override fun apply(value: String): ObservableSource<String> {
                return Observable.create(object : ObservableOnSubscribe<String> {
                    override fun subscribe(e: ObservableEmitter<String>?) {
                        Log.d(TAG, "flatMap 操作符 Int 转 ObservableOnSubscribe")
                        //flatMap操作符不是顺序执行的
                        for (i in 1..10) {
                            e?.onNext(value + (100 * i))
                        }
                    }
                }).delay(1, TimeUnit.SECONDS)
            }
        })
            .subscribe(object : Consumer<String> {
                override fun accept(t: String) {
                    Log.d(TAG, "flatMap 操作符 accept() =  $t")
                }
            })
    }

    private fun concatMapOption() {
        Observable.create(object : ObservableOnSubscribe<String> {
            override fun subscribe(e: ObservableEmitter<String>?) {
                //只会接收第一个onNext ，后面的会被丢弃
                e?.onNext("A ")
                e?.onNext("B ")
                e?.onNext("C ")
                e?.onComplete()
            }
        })
            .concatMap(object : Function<String, ObservableSource<String>> {
                override fun apply(value: String): ObservableSource<String> {
                    return Observable.create(object : ObservableOnSubscribe<String> {
                        override fun subscribe(e: ObservableEmitter<String>?) {
                            Log.d(TAG, "concatMap 操作符 Int 转 ObservableOnSubscribe")
                            //flatMap操作符不是顺序执行的
                            for (i in 1..10) {
                                e?.onNext(value + (100 * i))
                            }
                        }
                    }).delay(1, TimeUnit.SECONDS)
                }
            })
            .subscribe(object : Consumer<String> {
                override fun accept(t: String) {
                    Log.d(TAG, "concatMap 操作符 accept() =  $t")
                }
            })
    }

    /**
     * groupBy操作符，先发射true的一组数据，再返回false的一组数据
     */
    private fun groupByOption() {
        Observable.just(12, 45, 88, 54, 67, 93, 54, 34, 85)
            .groupBy(object : Function<Int, String> {
                override fun apply(num: Int): String {
                    return if (num >= 60) "达到及格分数" else "未及格"
                }
            })
            .subscribe(object : Consumer<GroupedObservable<String, Int>> {
                override fun accept(t: GroupedObservable<String, Int>) {
                    Log.d(TAG, "groupBy 操作符*** key= ${t.key}")
                    //只能拿到key,要拿value还要进行下面操作
                    t.subscribe(object : Consumer<Int> {
                        override fun accept(result: Int) {
                            Log.d(TAG, "groupBy 操作符--- key= ${t.key}  value=$result")
                        }
                    })
                }
            })
    }

    /**
     * buffer操作符 ，多个发射时间，不想一次接收，先缓存到buffer
     */
    private fun bufferOption() {
        Observable.create(object : ObservableOnSubscribe<Int> {
            override fun subscribe(e: ObservableEmitter<Int>?) {
                for (i in 1..50) {
                    e?.onNext(i)
                }
            }
        })
            .buffer(10)
            .subscribe(object : Consumer<List<Int>> {
                override fun accept(t: List<Int>) {
                    Log.d(TAG, "buffer 操作符 接收=$t")
                }
            })
    }
}