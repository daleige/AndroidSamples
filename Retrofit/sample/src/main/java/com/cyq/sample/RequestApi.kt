package com.cyq.sample

import retrofit2.Call
import retrofit2.http.GET

interface RequestApi {
    @GET("/anner/json")
    fun getBanner(): Call<String>
}