package com.cyq.jetpack.paging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.cyq.jetpack.R
import com.cyq.jetpack.databinding.ActivityPageKeyedBinding
import com.cyq.jetpack.paging.api.RetrofitClient
import com.cyq.jetpack.paging.model.Item
import com.cyq.jetpack.paging.paging.UserPageListAdapter
import com.cyq.jetpack.paging.paging.UserViewModel
import kotlinx.android.synthetic.main.activity_page_keyed.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * PagekeyedDataSource实现Paging分页
 */
class PageKeyedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityPageKeyedBinding>(this, R.layout.activity_page_keyed)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val userPageListAdapter = UserPageListAdapter()
        val userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.liveDataSource.observe(this, object : Observer<PagedList<Item>> {
            override fun onChanged(t: PagedList<Item>?) {
                userPageListAdapter.submitList(t)
            }
        })
        recyclerView.adapter = userPageListAdapter

//        CoroutineScope(Dispatchers.Main).launch {
//            RetrofitClient.getUserInfoApi()
//        }
//        }
    }
}