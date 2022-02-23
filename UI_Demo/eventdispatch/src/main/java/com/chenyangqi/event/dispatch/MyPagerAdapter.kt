package com.chenyangqi.event.dispatch

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.viewpager.widget.PagerAdapter
import java.util.*

internal class MyPagerAdapter(private val views: List<View>) : PagerAdapter() {
    override fun getCount(): Int {
        return views.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = views[position]
        val colors = intArrayOf(
            Color.YELLOW,
            Color.GREEN,
            Color.RED,
            Color.WHITE
        )
        view.setBackgroundColor(colors[position])
        val listView = view.findViewById<ListView>(R.id.listView1)
        val strList: MutableList<String> = ArrayList()
        for (i in 0..99) {
            strList.add("item $i")
        }
        val adapter = ItemAdapter(container.context, R.layout.simple_list_item, strList)
        listView.adapter = adapter
        container.addView(view)
        return view
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(views[position])
    }
}