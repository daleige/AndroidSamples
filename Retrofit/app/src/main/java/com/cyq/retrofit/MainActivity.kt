package com.cyq.retrofit

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cyq.lib_network.RetrofitManager
import com.cyq.lib_network.BaseBody
import dalvik.system.DexClassLoader
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.lang.reflect.Method

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnPostFormat.setOnClickListener(this)
        btnReflex.setOnClickListener(this)
        //startActivity(Intent(this,TestActivity::class.java))
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnPostFormat -> postFormat()
            R.id.btnReflex -> get()
        }
    }


    private fun postFormat() {
        RetrofitManager.getInstance()
            .setRequest(RequestAPI::class.java)
            .getPersonInfo(1231231, "张安")
            .enqueue(object : Callback<BaseBody<Person>> {
                override fun onResponse(
                    call: Call<BaseBody<Person>>, response: Response<BaseBody<Person>>
                ) {
                    Toast.makeText(this@MainActivity, "请求成功", Toast.LENGTH_SHORT).show()
                    val person: Person = response.body()?.data ?: Person()
                    Log.d("test", "结果为：$person")
                }

                override fun onFailure(call: Call<BaseBody<Person>>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "请求失败", Toast.LENGTH_SHORT).show()
                    Log.d("test", "请求失败！")
                    t.printStackTrace()
                }
            })
    }


    private fun get() {
        val path =
            getExternalFilesDir(null)?.absolutePath + File.separator + "ColorOS" + File.separator + "IoT" + File.separator + "testsdk" + File.separator + "sdk.ov.midea.com.mideaovsdk_2_85.apk"
        Log.d(
            "test",
            "地址：$path"
        )
        val oDexFile = File(getDir("odex", MODE_PRIVATE).absolutePath)
        Log.d("test", "oDexFile:$oDexFile")
        val dexClassLoader: ClassLoader = DexClassLoader(
            path,
            oDexFile.absolutePath,
            null,
            classLoader
        )
        val c = dexClassLoader.loadClass("sdk.ov.midea.com.mideaovsdk.IotPluginImp")
        val method: Method = c.getDeclaredMethod("onCreate")
        method.invoke(null, null, null)
    }
}
