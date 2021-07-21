package com.chenyangqi.nested.scroll.nesting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.chenyangqi.nested.scroll.R
import kotlinx.android.synthetic.main.activity_nessting_scroll.*

class NesstingScrollActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nessting_scroll)

        val tabNames = arrayOf("Tab-1", "Tab-2", "Tab-3")
        val fragments = arrayListOf<Fragment>()
        for (name in tabNames) {
            fragments.add(TabFragment())
            mTabLayout.addTab(mTabLayout.newTab())
        }
        mTabLayout.setupWithViewPager(mViewPager, false)
        mViewPager.adapter = TabLayoutAdapter(supportFragmentManager, fragments)
        for (index in tabNames.indices) {
            mTabLayout.getTabAt(index)?.text = tabNames[index]
        }
    }
}