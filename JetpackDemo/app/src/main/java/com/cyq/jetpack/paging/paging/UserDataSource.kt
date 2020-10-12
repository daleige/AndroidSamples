package com.cyq.jetpack.paging.paging

import androidx.paging.PageKeyedDataSource
import com.cyq.jetpack.paging.api.RetrofitClient
import com.cyq.jetpack.paging.model.Item
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 *    @author : ChenYangQi
 *    date   : 2020/10/12 15:54
 *    desc   :
 */
class UserDataSource : PageKeyedDataSource<Int, Item>() {
    companion object {
        const val FIRST_PAGE = 1
        const val PER_PAGE = 8
        const val SITE = "stackoverflow"
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Item>
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val user = RetrofitClient.getUserInfoApi()
                .getUsers(FIRST_PAGE, PER_PAGE, SITE)
            //第一个参数是得到的数据，把他交给数据列表
            //第二个参数是上一页的key,这里是初次加载所以直接设置为null
            //第三个参数为下一页的key
            callback.onResult(user.items, null, FIRST_PAGE + 1)
        }
    }

    /**
     * params.key=loadInitial()中专递过来的nextPageKey
     */
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {
        CoroutineScope(Dispatchers.IO).launch {
            val user = RetrofitClient.getUserInfoApi()
                .getUsers(params.key, PER_PAGE, SITE)
            val nextKey: Int? = if (user.has_more) params.key + 1 else null
            callback.onResult(user.items, nextKey)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {

    }
}