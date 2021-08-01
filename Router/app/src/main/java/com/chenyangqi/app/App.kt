package com.chenyangqi.app

import android.app.Application
import com.chenyangqi.router_runtime.Router

/**
 * @author : ChenYangQi
 * date   : 7/13/21 23:39
 * desc   :
 */
class App : Application() {


    override fun onCreate() {
        super.onCreate()
        //初始化路由组件
        Router.init()
    }
}