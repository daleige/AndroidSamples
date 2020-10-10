package com.cyq.jetpack.binding_adapter

import android.graphics.Color
import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 *    @author : ChenYangQi
 *    date   : 2020/10/10 16:18
 *    desc   : 自定义图片加载的BindingAdapter
 */
object ImageViewBindingAdapter {

    @JvmStatic
    @BindingAdapter("image")
    fun setImage(imageView: ImageView, imgUri: String) {
        if (!TextUtils.isEmpty(imgUri)) {
            Glide.with(imageView.context)
                .load(imgUri)
                .into(imageView)
        } else {
            imageView.setBackgroundColor(Color.GRAY)
        }
    }
}