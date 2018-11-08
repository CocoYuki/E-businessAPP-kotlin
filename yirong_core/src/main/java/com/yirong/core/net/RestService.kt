package com.yirong.core.net

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import java.util.*


/**
 * Created by yiron on 2018/10/31.
 */
public interface RestService {
    @GET
    fun get(@Url url:String,@QueryMap options:WeakHashMap<String,Any>): Call<String>

    @FormUrlEncoded
    @POST
    abstract fun post(@Url url: String, @FieldMap params: WeakHashMap<String, Any>): Call<String>

    @POST
    abstract fun postRaw(@Url url: String, @Body body: ResponseBody?): Call<String>

    @FormUrlEncoded
    @PUT
    abstract fun put(@Url url: String, @FieldMap params: Map<String, Any>): Call<String>

    @PUT
    abstract fun putRaw(@Url url: String, @Body body: ResponseBody?): Call<String>

    @DELETE
    abstract fun delete(@Url url: String, @QueryMap options: Map<String, Any>): Call<String>


    @Streaming //文件下载方式是，下载完成之前先缓存在内存中，然后写入文件，所以当文件过大时，会出现内存溢出，使用Streaming注解表示，一边下载。一边写入文件。
    @GET
    abstract fun download(@Url url: String, @QueryMap options: WeakHashMap<String, Any>): Call<ResponseBody>

    @Multipart
    @POST
    abstract fun upload(@Url url: String, @Part file: MultipartBody.Part): Call<String>
}