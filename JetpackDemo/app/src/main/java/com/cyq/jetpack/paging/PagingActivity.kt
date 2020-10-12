package com.cyq.jetpack.paging

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.cyq.jetpack.R
import com.cyq.jetpack.databinding.ActivityPagingBinding
import com.cyq.jetpack.paging.model.Subject
import com.cyq.jetpack.paging.paging.MoviePagedListAdapter
import com.cyq.jetpack.paging.paging.MovieViewModel
import kotlinx.android.synthetic.main.activity_paging.*

class PagingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pagingBinding: ActivityPagingBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_paging)

        val moviePagedListAdapter = MoviePagedListAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        val movieViewModel: MovieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        movieViewModel.moviePageList.observe(this, object : Observer<PagedList<Subject>> {
            override fun onChanged(t: PagedList<Subject>?) {
                moviePagedListAdapter.submitList(t)
            }
        })
        recyclerView.adapter = moviePagedListAdapter
    }
}