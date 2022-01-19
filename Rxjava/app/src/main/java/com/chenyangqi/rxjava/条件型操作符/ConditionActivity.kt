package com.chenyangqi.rxjava.条件型操作符

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.chenyangqi.rxjava.R
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import io.reactivex.functions.Predicate

@SuppressLint("CheckResult")
class ConditionActivity : AppCompatActivity() {
    companion object {
        const val TAG = "ConditionActivity_TAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_condition)

        allOption()
        containerOption()
        anyOption()
    }

    /**
     * 都满足条件返回true
     */
    private fun allOption() {
        Observable.just(1, 2, 4, 5, 6, 8, 12)
            .all(object : Predicate<Int> {
                override fun test(t: Int): Boolean {
                    return t > 10
                }
            })
            .subscribe(object : Consumer<Boolean> {
                override fun accept(t: Boolean) {
                    Log.d(TAG, "all 操作符 $t")
                }
            })
    }

    private fun anyOption() {
        Observable.just("kobe", "just", "alis", "java")
            .contains("java")
            .subscribe(object : Consumer<Boolean> {
                override fun accept(t: Boolean) {
                    Log.d(TAG, "contains 操作符 $t")
                }
            })
    }

    private fun containerOption() {
        Observable.just(1, 2, 4, 5, 6, 8, 12)
            .any(object : Predicate<Int> {
                override fun test(t: Int): Boolean {
                    return t > 10
                }
            })
            .subscribe(object : Consumer<Boolean> {
                override fun accept(t: Boolean) {
                    Log.d(TAG, "all 操作符 $t")
                }
            })
    }
}