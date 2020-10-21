package com.cyq.jetpack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.cyq.jetpack.paging.PageKeyedActivity
import com.cyq.jetpack.paging.PositionalPagingActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDataBinding.setOnClickListener(this)
        btnPaging.setOnClickListener(this)
        btnPaging2.setOnClickListener(this)
        btnThread.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnDataBinding ->
                startActivity(Intent(this, DataBindingActivity::class.java))
            R.id.btnPaging ->
                startActivity(Intent(this, PositionalPagingActivity::class.java))
            R.id.btnPaging2 ->
                startActivity(Intent(this, PageKeyedActivity::class.java))
            R.id.btnThread ->
                startActivity(Intent(this, ThreadActivity::class.java))
        }
    }
}