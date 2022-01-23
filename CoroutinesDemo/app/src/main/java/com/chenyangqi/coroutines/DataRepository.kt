package com.chenyangqi.coroutines

import com.chenyangqi.coroutines.bean.DataResponseBean
import com.google.gson.Gson
import java.lang.RuntimeException
import java.net.HttpURLConnection
import java.net.URL

/**
 * @author : ChenYangQi
 * date   : 2022/1/23 19:30
 * desc   :
 */
class DataRepository() {
    private val requestUrl = "https://www.wanandroid.com/article/list/0/json"

    fun requestData(): Result<DataResponseBean> {
        val url = URL(requestUrl)
        (url.openConnection() as? HttpURLConnection)?.run {
            requestMethod = "GET"
            setRequestProperty("Content-Type", "application/json; utf-8")
            setRequestProperty("Accept", "application/json")
            connect()
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val inputStream = inputStream
                val bytes = ByteArray(inputStream.available())
                inputStream.read(bytes)
                val result = String(bytes)
                val dataResponseBean: DataResponseBean =
                    Gson().fromJson(result, DataResponseBean::class.java)
                return Result.Success(dataResponseBean)
            }
        }
        return Result.Error(RuntimeException("获取数据失败！"));
    }

}