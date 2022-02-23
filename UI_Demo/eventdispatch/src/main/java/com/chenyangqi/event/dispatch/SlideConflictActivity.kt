package com.chenyangqi.event.dispatch

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import java.util.ArrayList

class SlideConflictActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slide_conflict)

        val views = mutableListOf<View>()
        views.add(layoutInflater.inflate(R.layout.pager_container, null))
        views.add(layoutInflater.inflate(R.layout.pager_container, null))
        views.add(layoutInflater.inflate(R.layout.pager_container, null))
        findViewById<MyViewPager>(R.id.viewPage).adapter = MyPagerAdapter(views)
        Log.d("test_y", "----0")
        val listView2 = findViewById<MyListView>(R.id.listView2)
        val strList: MutableList<String> = ArrayList()
        for (i in 0..99) {
            strList.add("listView2 item $i")
        }
        val adapter = ItemAdapter(this, R.layout.simple_list_item, strList)
        listView2.adapter = adapter
    }
}