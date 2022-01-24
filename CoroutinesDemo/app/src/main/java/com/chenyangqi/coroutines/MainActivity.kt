package com.chenyangqi.coroutines

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

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

        lifecycleScope.launch {
            makeFlow().collect { value ->
                when (value) {
                    "str1" -> {
                        Log.d("test", "${Thread.currentThread().name} collect str1")
                    }
                    "str2" -> {
                        Log.d("test", "${Thread.currentThread().name} collect str2")
                    }
                }
            }
        }

        simple()
    }

    /**
     * flowOn切换线程是切换的collect前面的线程
     */
    private fun simple() {
        lifecycleScope.launch(Dispatchers.Main) {
            flow {
                for (i in 1..5) {
                    delay(100)
                    emit(i)
                }
            }.map {
                it * it
            }.flowOn(Dispatchers.IO)//flowOn切换线程
                .collect {
                    Log.d("test", "${Thread.currentThread().name}: $it")
                }
        }
    }

    private fun makeFlow() = flow {
        Log.d("test", "${Thread.currentThread().name}emit str1")
        emit("str1")

        Log.d("test", "${Thread.currentThread().name}emit str2")
        emit("str2")
    }.flowOn(Dispatchers.IO)
}