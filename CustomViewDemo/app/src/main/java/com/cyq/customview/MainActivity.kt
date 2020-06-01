package com.cyq.customview

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.cyq.customview.blur.BlurActivity
import com.cyq.customview.drawText.DrawTextActivity
import com.cyq.customview.flowLayout.FlowLayoutActivity
import com.cyq.customview.intercept.InterceptActivity
import com.cyq.customview.nineLayout.NineImageLayoutActivity
import com.cyq.customview.paintView.PaintViewActivity
import com.cyq.customview.shadowLayout.ShadowLayoutActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        mBtnShadowLayout.setOnClickListener(this)
        mBtnDrawText.setOnClickListener(this)
        mBtnPaintView.setOnClickListener(this)
        mBtnFlowLayout.setOnClickListener(this)
        mBtnNineLayout.setOnClickListener(this)
        mBtnBlue.setOnClickListener(this)
        mBtnIntercept.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val intent = Intent()
        when (v.id) {
            R.id.mBtnShadowLayout -> {
                intent.setClass(this, ShadowLayoutActivity::class.java)
                startActivity(intent)
            }
            R.id.mBtnDrawText -> {
                intent.setClass(this, DrawTextActivity::class.java)
                startActivity(intent)
            }
            R.id.mBtnPaintView -> {
                intent.setClass(this, PaintViewActivity::class.java)
                startActivity(intent)
            }
            R.id.mBtnFlowLayout -> {
                intent.setClass(this, FlowLayoutActivity::class.java)
                startActivity(intent)
            }
            R.id.mBtnNineLayout -> {
                intent.setClass(this, NineImageLayoutActivity::class.java)
                startActivity(intent)
            }
            R.id.mBtnBlue -> {
                intent.setClass(this, BlurActivity::class.java)
                startActivity(intent)
            }
            R.id.mBtnIntercept -> {
                intent.setClass(this, InterceptActivity::class.java)
                startActivity(intent)
            }
            else -> {
            }
        }
    }
}