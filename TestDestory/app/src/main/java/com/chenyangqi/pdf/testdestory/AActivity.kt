package com.chenyangqi.test.destorydemo

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.chenyangqi.pdf.testdestory.R

class AActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a)
    }

    fun jumpToActivityB(view: View) {
        startActivity(Intent(this, BActivity::class.java))
    }
}