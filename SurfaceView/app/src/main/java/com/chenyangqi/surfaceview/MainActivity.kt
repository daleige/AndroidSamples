package com.chenyangqi.surfaceview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.ImageView
import com.chenyangqi.surfaceview.frame_anim.Util

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val frameImgView = findViewById<ImageView>(R.id.frameImgView)


        findViewById<Button>(R.id.btnStart).setOnClickListener {
            val anim = Util.getResources(this)
            frameImgView.setImageDrawable(anim)
            anim.start()
        }
    }
}