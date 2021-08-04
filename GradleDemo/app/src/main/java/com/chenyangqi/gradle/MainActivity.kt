package com.chenyangqi.gradle

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.chenyangqi.gradle.test.Test1
import com.chenyangqi.gradle.test.Test2
import com.chenyangqi.gradle.test.TestUtils

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Test1().getName()
        Test2().getName()
        TestUtils().test2()

        Log.d("test", "BaseUrl=${BuildConfig.baseUrl}")
        Log.d("test", "isSpeed=${BuildConfig.isSpeed}")
        Log.d("test", "MTA_CHANNEL=${getChannelInfo()}")
    }

    private fun getChannelInfo(): String? {
        val appInfo = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA)
        return appInfo.metaData.getString("MTA_CHANNEL")
    }

}