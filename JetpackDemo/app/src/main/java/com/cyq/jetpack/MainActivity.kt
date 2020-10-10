package com.cyq.jetpack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDataBinding.setOnClickListener(this)
        btnPaging.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnDataBinding ->
                startActivity(Intent(this, DataBindingActivity::class.java))
            R.id.btnPaging ->
                startActivity(Intent(this, PagingActivity::class.java))
        }
    }
}