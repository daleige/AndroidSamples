package com.chenyangqi.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    suspend fun test() {
        supervisorScope {
            launch { }
            launch { }
        }
    }
}