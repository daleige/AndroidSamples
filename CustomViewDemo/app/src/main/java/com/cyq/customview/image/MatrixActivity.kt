package com.cyq.customview.image

import android.content.Context
import android.graphics.Camera
import android.graphics.Matrix
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.cyq.customview.R
import kotlinx.android.synthetic.main.activity_matrix.*

class MatrixActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matrix)

        btnEditImg.setOnClickListener {
            matrixImage()
        }
    }

    private fun matrixImage() {
        imgResult.setImageResource(R.drawable.idcard)
        imgResult.scaleType = ImageView.ScaleType.MATRIX
        rotateImg()
    }

    /**
     * 设置旋转中心非常必要
     * @param imageView
     */
    private fun rotateImg() {
        val matrix: Matrix = getMatrix(-45)
        // 旋转中心为(0,height/2)
//        matrix.preTranslate(0F, -imgResult.height / 2F)
//        matrix.postTranslate(0F, imgResult.height / 2F)
        imgResult.imageMatrix = matrix
        imgResult.invalidate()
    }

    private fun getMatrix(rotate: Int): Matrix {
        val matrix = Matrix()
        val camera = Camera()
        camera.save()
        camera.rotateY(rotate.toFloat())
        camera.getMatrix(matrix)
        camera.restore()
        return matrix
    }
}