package com.cyq.jetpack.paging.api

import com.cyq.jetpack.paging.model.Movies
import com.cyq.jetpack.paging.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author : ChenYangQi
 * date   : 2020/10/11 22:39
 * desc   :
 */
interface Api {

    /**
     * 获取影院当前上映的电影
     */
    @GET("movie/in_theaters")
    suspend fun getMovies(@Query("start") since: Int, @Query("count") perPage: Int): Movies

    @GET("2.2/users")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("pagesize") pageSize: Int,
        @Query("site") site: String
    ): User
}