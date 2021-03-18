package com.cyq.retrofit

import android.app.Application
import com.cyq.lib_network.RetrofitManager

/**
 * @describe xxx
 * @author chenyq113@midea.com
 * @time 2021/1/28 17:46
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        RetrofitManager.getInstance().initClient(applicationContext)
    }

}