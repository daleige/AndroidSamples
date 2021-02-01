package com.cyq.retrofit

import com.cyq.lib_network.BaseResult
import com.cyq.retrofit.bean.Person
import com.cyq.retrofit.bean.PersonBean
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * @describe xxx
 * @author chenyq113@midea.com
 * @time 2021/1/28 17:47
 */
interface RequestAPI {

    /**
     * 获取个人信息
     */
    @FormUrlEncoded
    @POST("/getPersonInfo")
    fun getPersonInfo(@Field("id") id: Int, @Field("name") name: String): Call<PersonBean>

    @FormUrlEncoded
    @POST("/getPersonInfo")
    fun getPersonInfo2(@Field("id") id: Int, @Field("name") name: String): Call<BaseResult<Person>>
}