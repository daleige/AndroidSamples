package com.cyq.retrofit

import com.cyq.lib_network.AppConfig
import com.cyq.lib_network.BaseResult
import com.cyq.retrofit.bean.DeviceInfo
import com.cyq.retrofit.bean.Person
import com.cyq.retrofit.bean.PersonBean
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

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

    @FormUrlEncoded
    @POST("/getPersonInfo")
    fun getPersonInfo3(@Field("id") id: Int, @Field("name") name: String): Call<String>

    @GET("/getDeviceInfo")
    fun getDeviceInfo(@Query("deviceId") deviceId: String): Call<DeviceInfo>

    @Headers(AppConfig.CONTENT_TYPE_JSON)
    @POST("/postJson")
    fun postJson(@Body requestBody: RequestBody): Call<DeviceInfo>
}