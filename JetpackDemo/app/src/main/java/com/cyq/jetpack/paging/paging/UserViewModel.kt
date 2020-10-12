package com.cyq.jetpack.paging.paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.cyq.jetpack.paging.model.Item

/**
 *    @author : ChenYangQi
 *    date   : 2020/10/12 16:35
 *    desc   :
 */
class UserViewModel : ViewModel() {

     val liveDataSource: LiveData<PagedList<Item>>

    init {
        val config: PagedList.Config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(PER_PAGE)
            //设置距离底部预加载数据
            .setPrefetchDistance(3)
            //设置首次加载数据的数量，改值要求是PageSize的整数倍，未设置则默认为PageSize的3倍
            .setInitialLoadSizeHint(PER_PAGE * 4)
            //设置PageList所能承受的数量，一般来说是PageSize的许多倍，超过该值可能会出现异常
            .setMaxSize(65536 * PER_PAGE)
            .build()
        liveDataSource = (LivePagedListBuilder(UserDataSourceFactory(), config).build())
    }

}