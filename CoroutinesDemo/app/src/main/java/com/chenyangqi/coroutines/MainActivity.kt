package com.chenyangqi.coroutines

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val model: MainViewModel by viewModels()
        findViewById<AppCompatButton>(R.id.btnRequestData).setOnClickListener {
            when (val result = model.updateTaps()) {
                is Result.Success -> {
                    Toast.makeText(this, "请求数据成功", Toast.LENGTH_SHORT).show()
                    Log.d("test", "结果=${Gson().toJson(result.data)}")
                }
                else -> {
                    Toast.makeText(this, "请求数据失败！", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}