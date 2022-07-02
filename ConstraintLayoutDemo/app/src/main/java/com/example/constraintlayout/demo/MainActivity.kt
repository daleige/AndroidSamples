package com.example.constraintlayout.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.constraintlayout.helper.widget.Layer
import androidx.constraintlayout.widget.Group
import androidx.constraintlayout.widget.Placeholder
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_group).setOnClickListener {
            val group = findViewById<Group>(R.id.group)
            if (group.isVisible) {
                group.visibility = View.GONE
            } else {
                group.visibility = View.VISIBLE
            }
        }

        findViewById<Button>(R.id.btn_layer).setOnClickListener {
            val layer = findViewById<Layer>(R.id.layer)
            layer.translationX = 400F
        }

        findViewById<View>(R.id.btn_placeholder).setOnClickListener {
            findViewById<Placeholder>(R.id.placeholder).setContentId(it.id)
        }
    }
}