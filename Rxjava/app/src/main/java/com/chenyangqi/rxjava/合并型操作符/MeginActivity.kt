package com.chenyangqi.rxjava.合并型操作符

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.chenyangqi.rxjava.R
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

/**
 * 合并性操作符
 */
@SuppressLint("CheckResult")
class MeginActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MeginActivity_TAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_megin)
    }

    /**
     * startWith操作符：先执行startWith里面的被观察者，在执行外面的被观察者
     */
    fun startWith(view: View) {
        Observable.create(object : ObservableOnSubscribe<Int> {
            override fun subscribe(e: ObservableEmitter<Int>?) {
                e?.onNext(1)
                e?.onNext(2)
                e?.onNext(3)
                e?.onComplete()
            }
        })
            .startWith(Observable.create(object : ObservableOnSubscribe<Int> {
                override fun subscribe(e: ObservableEmitter<Int>?) {
                    e?.onNext(100)
                    e?.onNext(200)
                    e?.onNext(300)
                    //必须调用onComplete，否则外面的被观察者会被一直挂起等待执行
                    e?.onComplete()
                }
            }))
            .subscribe(object : Consumer<Int> {
                override fun accept(t: Int) {
                    Log.d(TAG, "startWith 操作符 result=$t")
                }
            })
    }

    /**
     * concatWith操作符:与startWith操作符相反，先执行外面的被观察者，再执行concatWith里面的被观察者
     */
    fun concatWith(view: View) {
        Observable.create(object : ObservableOnSubscribe<Int> {
            override fun subscribe(e: ObservableEmitter<Int>?) {
                e?.onNext(1)
                e?.onNext(2)
                e?.onNext(3)
                e?.onComplete()
            }
        })
            .concatWith(Observable.create(object : ObservableOnSubscribe<Int> {
                override fun subscribe(e: ObservableEmitter<Int>?) {
                    e?.onNext(100)
                    e?.onNext(200)
                    e?.onNext(300)
                    e?.onComplete()
                }
            }))
            .subscribe(object : Consumer<Int> {
                override fun accept(t: Int) {
                    Log.d(TAG, "concatWith 操作符 result=$t")
                }
            })

    }

    /**
     * concat操作符：最多能合并四个被观察者，按照顺序依次执行
     */
    fun concat(view: View) {
        Observable.concat(
            Observable.just(1),
            Observable.create(object : ObservableOnSubscribe<Int> {
                override fun subscribe(e: ObservableEmitter<Int>?) {
                    Thread.sleep(1000)
                    e?.onNext(2)
                    e?.onComplete()
                }
            }),
            Observable.just(3),
            Observable.just(4)
        )
            .subscribe(object : Consumer<Int> {
                override fun accept(t: Int) {
                    Log.d(TAG, "concat操作符 $t")
                }
            })

        //栗子2
        Observable.concat(
            Observable.create(object : ObservableOnSubscribe<Int> {
                override fun subscribe(e: ObservableEmitter<Int>?) {
                    Log.d(TAG, "被观察者1 begin...")
                    Thread.sleep(2000)
                    Log.d(TAG, "被观察者1 end...")
                    e?.onNext(1)
                    e?.onComplete()
                }
            }).subscribeOn(Schedulers.io()),
            Observable.create(object : ObservableOnSubscribe<Int> {
                override fun subscribe(e: ObservableEmitter<Int>?) {
                    Log.d(TAG, "被观察者2 begin...")
                    Thread.sleep(1500)
                    Log.d(TAG, "被观察者2 end...")
                    e?.onNext(2)
                    e?.onComplete()
                }
            }).subscribeOn(Schedulers.io()),
            Observable.create(object : ObservableOnSubscribe<Int> {
                override fun subscribe(e: ObservableEmitter<Int>?) {
                    Log.d(TAG, "被观察者3 begin...")
                    Thread.sleep(1000)
                    Log.d(TAG, "被观察者3 end...")
                    e?.onNext(3)
                    e?.onComplete()
                }
            }).observeOn(Schedulers.io())
        )
            .subscribe(object : Consumer<Int> {
                override fun accept(t: Int) {
                    Log.d(TAG, "concat 操作符 value=$t")
                }
            })
    }

    /**
     * concat操作符：最多能合并四个被观察者，同步执行
     */
    fun merge(view: View) {
        Observable.merge(
            Observable.create(object : ObservableOnSubscribe<Int> {
                override fun subscribe(e: ObservableEmitter<Int>?) {
                    Log.d(TAG, "被观察者1 begin...")
                    Thread.sleep(2000)
                    Log.d(TAG, "被观察者1 end...")
                    e?.onNext(1)
                    e?.onComplete()
                }
            }).subscribeOn(Schedulers.io()),
            Observable.create(object : ObservableOnSubscribe<Int> {
                override fun subscribe(e: ObservableEmitter<Int>?) {
                    Log.d(TAG, "被观察者2 begin...")
                    Thread.sleep(1500)
                    Log.d(TAG, "被观察者2 end...")
                    e?.onNext(2)
                    e?.onComplete()
                }
            }).subscribeOn(Schedulers.io()),
            Observable.create(object : ObservableOnSubscribe<Int> {
                override fun subscribe(e: ObservableEmitter<Int>?) {
                    Log.d(TAG, "被观察者3 begin...")
                    Thread.sleep(1000)
                    Log.d(TAG, "被观察者3 end...")
                    e?.onNext(3)
                    e?.onComplete()
                }
            }).observeOn(Schedulers.io())
        )
            .subscribe(object : Consumer<Int> {
                override fun accept(t: Int) {
                    Log.d(TAG, "merge 操作符 value=$t")
                }
            })
    }

    /**
     * zip操作符：合并被观察者发射的事件，需要被观察者的发射事件一一对应
     */
    fun zip(view: View) {
        Observable.zip(
            Observable.just("英语", "语文", "数学"),
            Observable.just(139, 125, 138),
            object : BiFunction<String, Int, String> {
                override fun apply(t1: String, t2: Int): String {
                    return "$t1:$t2"
                }
            }
        )
            .subscribe(object : Consumer<String> {
                override fun accept(t: String) {
                    Log.d(TAG, "zip 操作符 value=$t")
                }
            })


        Observable.zip(
            Observable.create(object : ObservableOnSubscribe<String> {
                override fun subscribe(e: ObservableEmitter<String>?) {
                    Log.d(TAG, "被观察者1 begin...")
                    Thread.sleep(2000)
                    Log.d(TAG, "被观察者1 end...")
                    e?.onNext("语文")
                    e?.onComplete()
                }
            }).subscribeOn(Schedulers.io()),
            Observable.create(object : ObservableOnSubscribe<Int> {
                override fun subscribe(e: ObservableEmitter<Int>?) {
                    Log.d(TAG, "被观察者2 begin...")
                    Thread.sleep(1000)
                    Log.d(TAG, "被观察者2 end...")
                    e?.onNext(139)
                    e?.onComplete()
                }
            }).subscribeOn(Schedulers.io()),
            object : BiFunction<String, Int, String> {
                override fun apply(t1: String, t2: Int): String {
                    return "$t1:$t2"
                }
            }
        )
            .subscribe(object : Consumer<String> {
                override fun accept(t: String) {
                    Log.d(TAG, "zip 操作符 value=$t")
                }
            })


    }
}