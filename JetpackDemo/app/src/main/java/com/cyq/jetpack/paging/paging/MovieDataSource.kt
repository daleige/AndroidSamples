package com.cyq.jetpack.paging.paging

import androidx.paging.PositionalDataSource
import com.cyq.jetpack.paging.api.RetrofitClient
import com.cyq.jetpack.paging.model.Movies
import com.cyq.jetpack.paging.model.Subject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author : ChenYangQi
 * date   : 2020/10/11 23:15
 * desc   :
 */
class MovieDataSource : PositionalDataSource<Subject>() {
    private val PER_PAGE = 8;

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Subject>) {
        val startPosition = 0
        CoroutineScope(Dispatchers.IO).launch {
            RetrofitClient.getInstance()
                .getApi()
                .getMovies(startPosition, PER_PAGE)
//                .enqueue(object : Callback<Movies> {
//                    override fun onFailure(call: Call<Movies>, t: Throwable) {
//
//                    }
//
//                    override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
//
//                    }
//                })
        }
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Subject>) {


    }
}