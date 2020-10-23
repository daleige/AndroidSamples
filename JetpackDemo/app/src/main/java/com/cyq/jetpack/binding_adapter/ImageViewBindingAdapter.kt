package com.cyq.jetpack.binding_adapter

import android.graphics.Color
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.cyq.jetpack.R

/**
 *    @author : ChenYangQi
 *    date   : 2020/10/10 16:18
 *    desc   : 自定义图片加载的BindingAdapter
 */
object ImageViewBindingAdapter {

    /**
     * 单参数
     */
    @JvmStatic
    @BindingAdapter("image")
    fun setImage(imageView: ImageView, imgUri: String) {
        if (!TextUtils.isEmpty(imgUri)) {
            Glide.with(imageView.context)
                .load(imgUri)
                .placeholder(R.mipmap.ic_launcher)
                .into(imageView)
        } else {
            imageView.setBackgroundColor(Color.GRAY)
        }
    }

    /**
     * 多重参数重载
     */
    @JvmStatic
    @BindingAdapter(value = ["image2", "defaultImageResource"], requireAll = false)
    fun setDefaultImage(imageView: ImageView, imageUrl: String, imageResource: Int) {
        Log.i("test", "imageResource:$imageResource")
        if (!TextUtils.isEmpty(imageUrl)) {
            Glide.with(imageView.context)
                .load(imageUrl)
                .into(imageView)
        } else {
            Glide.with(imageView.context)
                .load(imageResource)
                .into(imageView)
        }
    }

    @JvmStatic
    @BindingAdapter("padingLeft")
    fun setPaddingLeft(view: View, paddingLeft: Int) {
        view.setPadding(paddingLeft, 0, 0, 0)
    }
}