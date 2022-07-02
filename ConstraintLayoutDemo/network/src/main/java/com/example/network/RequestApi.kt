package com.example.network

import com.example.network.bean.BannerBean
import com.example.network.bean.ChildBannerBean
import com.example.network.http.BaseRsp
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface RequestApi {
    @GET("/banner/json")
    fun getBanner(): Call<BannerBean>

    @GET("/banner/json")
    fun getBanner2(): Observable<BannerBean>

    @GET("/banner/json")
    fun getBanner3(): Observable<BaseRsp<List<ChildBannerBean>>>
}