package com.cyq.retrofit

import com.cyq.lib_network.AppConfig
import com.cyq.lib_network.BaseResult
import com.cyq.retrofit.bean.DeviceInfo
import com.cyq.retrofit.bean.Person
import com.cyq.retrofit.bean.PersonBean
import okhttp3.MultipartBody
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
    @GET
    fun downloadFile(@Url url: String): Call<ResponseBody>

    @Multipart
    @POST("fileUpload")
    fun upload(@Part parts: List<MultipartBody.Part?>?): Call<ResponseBody>
}