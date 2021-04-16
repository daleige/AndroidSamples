package com.cyq.customview.image

import android.content.Context
import android.graphics.Camera
import android.graphics.Matrix
import android.util.AttributeSet
import android.widget.ImageView

/**
 * @describe xxx
 * @author chenyq113@midea.com
 * @time 2021/4/16 17:33
 */
class SmartImageView @kotlin.jvm.JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        androidx.appcompat.widget.AppCompatImageView(context, attrs, defStyleAttr) {

    init {
        scaleType = ScaleType.MATRIX
    }

    /**
     * 设置旋转中心非常必要
     * @param imageView
     * @param rotateValue
     */
    private fun rotateImg(imageView: ImageView, rotateValue: Int) {
        val matrix: Matrix = getMatrix(rotateValue)
        if (rotateValue >= 0) {
            // 旋转中心为(0,height/2)
            matrix.preTranslate(0F, -imageView.height / 2F)
            matrix.postTranslate(0F, imageView.height / 2F)
        } else {
            // 旋转中心为(width,height/2)
            matrix.preTranslate(-imageView.width.toFloat(), -imageView.height / 2F)
            matrix.postTranslate(imageView.width.toFloat(), imageView.height / 2F)
        }
        imageView.imageMatrix = matrix
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