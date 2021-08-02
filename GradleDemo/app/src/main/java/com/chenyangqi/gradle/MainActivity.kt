package com.chenyangqi.gradle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chenyangqi.gradle.proguard.Test1
import com.chenyangqi.gradle.proguard.Test2
import com.chenyangqi.gradle.proguard.TestUtils

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Test1().getName()
        Test2().getName()
        TestUtils().test2()
    }

    private fun test() {
        println("-------A")
    }
}