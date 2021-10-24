package com.chenyangqi.rxjava.过滤型操作符

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.chenyangqi.rxjava.R
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.functions.Consumer
import io.reactivex.functions.Predicate
import kotlinx.android.synthetic.main.activity_filter.*
import java.util.concurrent.TimeUnit

@SuppressLint("CheckResult")
class FilterActivity : AppCompatActivity() {
    companion object {
        const val TAG = "FilterActivity_TAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)
        btnFilterOption.setOnClickListener { filterOption() }
        btnTakeOption.setOnClickListener { takeOption() }
        btnDistinctOption.setOnClickListener { distinctOption() }
        btnElementAtOption.setOnClickListener { elementAtOption() }
    }

    private fun filterOption() {
        Observable.just(65, 90, 54, 77, 44, 86)
            .filter(object : Predicate<Int> {
                override fun test(t: Int): Boolean {
                    return t >= 60
                }
            }).subscribe(object : Consumer<Int> {
                override fun accept(t: Int) {
                    Log.d(TAG, "filter  操作符 $t 及格")
                }
            })
    }

    /**
     * take操作符需要搭配定时器才能 生效
     */
    private fun takeOption() {
        Observable.interval(2, TimeUnit.SECONDS)
            .take(5)
            .subscribe(object : Consumer<Long> {
                override fun accept(t: Long) {
                    Log.d(TAG, "take 操作符 $t")
                }

            })
    }

    /**
     * 过滤重复发送的操作符
     */
    private fun distinctOption() {
        Observable.create(object : ObservableOnSubscribe<Int> {
            override fun subscribe(e: ObservableEmitter<Int>?) {
                e?.onNext(1)
                e?.onNext(2)
                e?.onNext(1)
                e?.onNext(3)
                e?.onNext(2)
                e?.onNext(3)
                e?.onComplete()
            }
        })
            .distinct()
            .subscribe(object : Consumer<Int> {
                override fun accept(t: Int) {
                    Log.d(TAG, "distinct 操作符 $t")
                }
            })
    }

    /**
     * 只取下标对应的发射的值
     */
    private fun elementAtOption() {
        Observable.create(object : ObservableOnSubscribe<String> {
            override fun subscribe(e: ObservableEmitter<String>?) {
                e?.onNext("九阴真经");
                e?.onNext("九阳真经");
                e?.onNext("易筋经");
                e?.onNext("神照经");
                e?.onComplete();
            }
        })
            .elementAt(2, "默认经")
            .subscribe(object : Consumer<String> {
                override fun accept(t: String) {
                    Log.d(TAG, "elementAt 操作符 $t")
                }
            })
    }
}