package com.yirong.core.net

import android.content.Context
import android.util.Log
import com.yirong.core.net.callback.*
import com.yirong.core.net.download.DownloadHandler
import com.yirong.core.ui.SugerLoader
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import java.io.File
import java.util.*

/**
 * Created by yiron on 2018/10/31.
 */
class RestClient(private val URL: String,
                 params: WeakHashMap<String, Any>,
                 private val SUCCESS: ISuccess,
                 private val ERROR: IError,
                 private val FAILURE: IFailure,
                 private val REQUEST: IRequest?,
                 private val REQUESTBODY: RequestBody?,
                 private val CONTEXT: Context,
                 private val BODY: ResponseBody?,
                 private val FILE: File?,
                 private val DOWNLOAD_DIR: String?,
                 private val EXTENSION: String?,
                 private val NAME: String?,
                 private val LOADSTYLE: String) {//建造者模式
    private val PARAMS = RestCreator.PARAMS
    companion object {
        fun getRestClientBuilder(): RestClientBuilder {
            return RestClientBuilder()
        }
    }
    fun request(method: HttpMethod){
        var call:Call<String>? = null;
        val restService = RestCreator.getRestService()
        if(REQUEST!=null){
            REQUEST.onRequestStart()
        }
        SugerLoader.showloader(CONTEXT,LOADSTYLE)
        when(method){
            HttpMethod.CET -> call = restService.get(URL,PARAMS)
            HttpMethod.POST -> call = restService.post(URL,PARAMS)
            HttpMethod.POST_RAW -> call = restService.postRaw(URL,BODY)
            HttpMethod.PUT -> call = restService.put(URL,PARAMS)
            HttpMethod.PUT_RAW -> call = restService.putRaw(URL,BODY)
            HttpMethod.DELETE -> call = restService.delete(URL,PARAMS)
            HttpMethod.UPLOAD -> {
                val requestBody = MultipartBody.create(MediaType.parse(MultipartBody.FORM.toString()),FILE)
                val body = MultipartBody.Part.createFormData("file",FILE?.name,requestBody)
                call = restService.upload(URL,body)
            }
        }

            call.enqueue(RequestCallbacks(SUCCESS, ERROR, FAILURE, REQUEST, LOADSTYLE))

    }
    /**请求方法调用**/
    fun get() {
        Log.i("get","this get")
        request(HttpMethod.CET)
    }

    fun post() {
        if (BODY == null) {
            request(HttpMethod.POST)
        } else {
            if (!PARAMS.isEmpty()) {
                throw RuntimeException("POST WRONG:params must be none!")
            } else {
                request(HttpMethod.POST_RAW)
            }
        }
    }

    fun put() {
        if (BODY == null) {
            request(HttpMethod.PUT)
        } else {
            if (!PARAMS.isEmpty()) {
                throw RuntimeException("PUT WRONG:params must be none!")
            } else {
                request(HttpMethod.PUT_RAW)
            }
        }
    }

    fun delete() {
        request(HttpMethod.DELETE)
    }

    fun upload() {
        request(HttpMethod.UPLOAD)
    }

    fun download() {
        DownloadHandler(URL, PARAMS, REQUEST, DOWNLOAD_DIR, EXTENSION, NAME,
                SUCCESS, FAILURE, ERROR)
                .handleDownload()
    }

    private fun getRequestCallback(): Callback<String> {
        return RequestCallbacks(SUCCESS, ERROR, FAILURE, REQUEST, LOADSTYLE)
    }
}