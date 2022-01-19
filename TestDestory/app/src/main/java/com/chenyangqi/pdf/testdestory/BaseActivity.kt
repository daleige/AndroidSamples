package com.chenyangqi.test.destorydemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.chenyangqi.pdf.testdestory.BaseAppManager

/**
 * @describe
 * @author chenyangqi
 * @time 2021/10/25 18:08
 */
open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BaseAppManager.getInstance().addActivity(this)

        Log.d("BaseAppManager_Log", this::class.simpleName + "------->onCreate()")
        Log.d("BaseAppManager_Log", "当前任务栈=" + BaseAppManager.getActivities())
    }

    override fun onDestroy() {
        BaseAppManager.getInstance().removeActivity(this)
        Log.d("BaseAppManager_Log", this::class.simpleName + "------->onDestroy()")
        super.onDestroy()
        Log.d("BaseAppManager_Log", "当前任务栈=" + BaseAppManager.getActivities())
    }
}