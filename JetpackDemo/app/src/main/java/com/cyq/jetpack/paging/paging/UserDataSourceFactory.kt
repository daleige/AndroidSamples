package com.cyq.jetpack.paging.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.cyq.jetpack.paging.model.Item

/**
 *    @author : ChenYangQi
 *    date   : 2020/10/12 16:30
 *    desc   : 使用LiveData包装UserDataSource
 */
class UserDataSourceFactory : DataSource.Factory<Int, Item>() {
    private val liveDataSource = MutableLiveData<UserDataSource>()

    override fun create(): DataSource<Int, Item> {
        val dataSource: UserDataSource = UserDataSource()
        liveDataSource.postValue(dataSource)
        return dataSource
    }
}