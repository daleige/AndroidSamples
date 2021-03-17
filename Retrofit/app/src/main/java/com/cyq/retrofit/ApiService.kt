package com.cyq.retrofit

import com.cyq.lib_network.AppConfig
import com.cyq.lib_network.BaseResult
import com.cyq.retrofit.bean.DeviceInfo
import com.cyq.retrofit.bean.Person
import com.cyq.retrofit.bean.PersonBean
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

/**
 * @describe xxx
 * @author chenyq113@midea.com
 * @time 2021/1/28 17:47
 */
interface ApiService {

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

    /**
     * 文件下载
     */
    @Streaming
    @GET("image_search/src=http%3A%2F%2Fa0.att.hudong.com%2F65%2F07%2F01300000204202121839075492554.jpg&refer=http%3A%2F%2Fa0.att.hudong.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1618560729&t=afd484393502d9a32b21b5db67cd5480")
    fun downloadFile(): Call<ResponseBody>
}