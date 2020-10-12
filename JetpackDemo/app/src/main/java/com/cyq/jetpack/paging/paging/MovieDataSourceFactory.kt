package com.cyq.jetpack.paging.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.cyq.jetpack.paging.model.Subject


/**
 *    @author : ChenYangQi
 *    date   : 2020/10/12 10:14
 *    desc   : LiveData包装MovieDataSource
 */
class MovieDataSourceFactory : DataSource.Factory<Int, Subject>() {
    private val liveDataSource = MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int, Subject> {
        val dataSource = MovieDataSource()
        liveDataSource.postValue(dataSource)
        return dataSource
    }
}