package com.yirong.core.net.download

import android.os.AsyncTask


import com.yirong.core.net.RestCreator
import com.yirong.core.net.callback.IError
import com.yirong.core.net.callback.IFailure
import com.yirong.core.net.callback.IRequest
import com.yirong.core.net.callback.ISuccess

import java.util.WeakHashMap

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by yiron on 2018/10/30.
 */

class DownloadHandler(private val URL: String,
                      private val PARAMS: WeakHashMap<String, Any>,
                      private val REQUEST: IRequest?,
                      private val DOWNLOAD_DIR: String?,
                      private val EXTENSION: String?,
                      private val NAME: String?,
                      private val SUCCESS: ISuccess,
                      private val FAILURE: IFailure?,
                      private val ERROR: IError?) {

    fun handleDownload() {
        REQUEST?.onRequestStart()

        RestCreator.getRestService()
                .download(URL, PARAMS)
                .enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        if (response.isSuccessful) {
                            val responseBody = response.body()
                            val task = SaveFileTask(REQUEST, SUCCESS)
                            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                                    DOWNLOAD_DIR, EXTENSION, responseBody, NAME)

                            //这里一定要注意判断，否则文件下载不全
                            if (task.isCancelled) {
                                REQUEST?.onRequestFinish()
                            }
                        } else {
                            ERROR?.onError(response.code(), response.message())
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        FAILURE?.onFailure()
                    }
                })
    }
}