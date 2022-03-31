package com.chenyangqi.surfaceview

import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.chenyangqi.surfaceview.frame_anim.Util

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val frameImgView = findViewById<ImageView>(R.id.frameImgView)

        findViewById<Button>(R.id.btnStart).setOnClickListener {
            val duration = 60
            val animationDrawable = AnimationDrawable()
            val intArray = Util.getSourceId()
            for (sourceId in intArray) {
                ContextCompat.getDrawable(this, sourceId)?.let {
                    animationDrawable.addFrame(it, duration)
                }
            }

            frameImgView.setImageDrawable(animationDrawable)
            animationDrawable.start()
        }
    }
}