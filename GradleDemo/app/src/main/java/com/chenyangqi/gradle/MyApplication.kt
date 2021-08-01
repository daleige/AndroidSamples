package com.chenyangqi.gradle

import android.content.Context
import androidx.multidex.MultiDex

/**
 * @author : ChenYangQi
 * date   : 2021/8/2 00:01
 * desc   :
 */
class MyApplication:OtherApplication() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}