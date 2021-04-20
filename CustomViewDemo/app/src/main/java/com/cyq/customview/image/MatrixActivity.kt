package com.cyq.customview.image

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cyq.customview.R
import kotlinx.android.synthetic.main.activity_matrix.*

class MatrixActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matrix)
        mSmartCropView.setImageResource(R.drawable.id_card)
    }
}