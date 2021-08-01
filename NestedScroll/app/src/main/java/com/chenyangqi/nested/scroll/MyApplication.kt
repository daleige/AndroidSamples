package com.chenyangqi.nested.scroll

import android.app.Application
import android.content.Context

/**
 * @describe
 * @author chenyangqi
 * @time 2021/7/22 15:11
 */
class MyApplication : Application() {


    companion object {
        private lateinit var mContext: Context
        fun getAppContext(): Context {
            return mContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext
    }
}