package com.cyq.animdemo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import com.cyq.animdemo.LayoutAnimation.MainActivity
import com.cyq.animdemo.pathmeasure.PathMeasureActivity
import com.cyq.animdemo.transition.OneActivity
import kotlinx.android.synthetic.main.activity_index.*

class IndexActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        window.allowEnterTransitionOverlap = true
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_index)
        mLayoutAnimation.setOnClickListener(this)
        mTransition.setOnClickListener(this)
        mBtnPathMeasure.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.mLayoutAnimation ->
                openActivity(MainActivity::class.java)
            R.id.mTransition -> {
                val intent2 = Intent(this@IndexActivity, OneActivity::class.java)
                startActivity(intent2, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle())
            }
            R.id.mBtnPathMeasure ->
                openActivity(PathMeasureActivity::class.java)
        }
    }

    private fun openActivity(clazz: Class<*>) {
        val intent = Intent(this, clazz)
        startActivity(intent)
    }
}