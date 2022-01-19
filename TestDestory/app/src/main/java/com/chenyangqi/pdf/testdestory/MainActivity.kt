package com.chenyangqi.test.destorydemo

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.chenyangqi.pdf.testdestory.R

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun jumpToActivityA(view: View) {
        startActivity(Intent(this, AActivity::class.java))
    }
}