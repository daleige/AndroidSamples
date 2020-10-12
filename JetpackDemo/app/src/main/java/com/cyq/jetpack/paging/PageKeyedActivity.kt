package com.cyq.jetpack.paging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.cyq.jetpack.R
import com.cyq.jetpack.databinding.ActivityPageKeyedBinding

/**
 * PagekeyedDataSource实现Paging分页
 */
class PageKeyedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityPageKeyedBinding>(this, R.layout.activity_page_keyed)


    }
}