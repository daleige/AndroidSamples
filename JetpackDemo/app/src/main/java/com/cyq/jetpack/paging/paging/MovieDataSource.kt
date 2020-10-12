package com.cyq.jetpack.paging.paging

import androidx.paging.PositionalDataSource
import com.cyq.jetpack.paging.api.RetrofitClient
import com.cyq.jetpack.paging.model.Subject
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @author : ChenYangQi
 * date   : 2020/10/11 23:15
 * desc   : 分页数据处理工具类
 */

const val PER_PAGE = 8

class MovieDataSource : PositionalDataSource<Subject>() {

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Subject>) {
        val startPosition = 0
        CoroutineScope(Dispatchers.IO).launch {
            val movies = RetrofitClient.getInstance()
                .getApi()
                .getMovies(startPosition, PER_PAGE)

            callback.onResult(movies.subjects, movies.start, movies.total)
        }
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Subject>) {
        CoroutineScope(Dispatchers.IO).launch {
            println("数据范围：${Gson().toJson(params)}")
            val movies = RetrofitClient.getInstance()
                .getApi()
                .getMovies(params.startPosition, PER_PAGE)

            callback.onResult(movies.subjects)
        }
    }
}