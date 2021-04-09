package com.cyq.peripheral

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnOpenLamp.setOnClickListener(this)
        btnCloseLamp.setOnClickListener(this)
        btnYellowLamp.setOnClickListener(this)
        btnGreenLamp.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnOpenLamp -> lampView.open()
            R.id.btnCloseLamp -> lampView.close()
            R.id.btnGreenLamp -> lampView.setLampColor(LampView.Color.GREEN)
            R.id.btnYellowLamp -> lampView.setLampColor(LampView.Color.YELLOW)
        }
    }
}