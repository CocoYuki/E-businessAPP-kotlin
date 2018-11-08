package com.yirong.core.net

import android.content.Context
import android.util.Log
import com.yirong.core.net.callback.IError
import com.yirong.core.net.callback.IFailure
import com.yirong.core.net.callback.IRequest
import com.yirong.core.net.callback.ISuccess
import com.yirong.core.ui.LoadingStyle
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import java.io.File
import java.util.*

/**
 * Created by yiron on 2018/10/31.
 */
class RestClientBuilder {
    private lateinit var mUrl: String
    private lateinit var mSuccess: ISuccess
    private lateinit var mError: IError
    private lateinit var mFailure: IFailure
    private var mRequest: IRequest? = null
    private var mRequestBody: RequestBody? = null
    private lateinit var mLoadStyle:String
    private lateinit var context: Context
    private val body: ResponseBody? = null
    private var mFile: File? = null
    private var mName: String? = null
    private var mDownloadDir: String? = null
    private var mExtension: String? = null


    fun url(url: String): RestClientBuilder {
        this.mUrl = url
        return this
    }

    fun getContext(context: Context): RestClientBuilder{
        this.context = context
        return this
    }
    fun params(params: WeakHashMap<String, Any>): RestClientBuilder {
        PARAMS.putAll(params)
        return this
    }

    fun params(key: String, value: Any): RestClientBuilder {
        PARAMS.put(key, value)
        return this
    }

    fun file(file: File): RestClientBuilder {
        this.mFile = file
        return this
    }

    fun file(file: String): RestClientBuilder {
        this.mFile = File(file)
        return this
    }

    fun name(name: String): RestClientBuilder {
        this.mName = name
        return this
    }

    fun dir(dir: String): RestClientBuilder {
        this.mDownloadDir = dir
        return this
    }

    fun extension(extension: String): RestClientBuilder {
        this.mExtension = extension
        return this
    }

    fun raw(raw: String): RestClientBuilder {
        this.mRequestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), raw)
        return this
    }

    fun success(iSuccess: ISuccess): RestClientBuilder {
        this.mSuccess = iSuccess
        return this
    }

    fun failure(iFailure: IFailure): RestClientBuilder {
        this.mFailure = iFailure
        return this
    }


    fun error(iError: IError): RestClientBuilder {
        this.mError = iError
        return this
    }

    fun request(iRequest: IRequest): RestClientBuilder {
        this.mRequest = iRequest
        return this
    }
    fun loadStyle():RestClientBuilder{
        return loadStyle(LoadingStyle.BallSpinFadeLoaderIndicator.name)
    }
    fun loadStyle(loadstyle:String):RestClientBuilder{
        this.mLoadStyle = loadstyle
        return this
    }


    fun build(): RestClient {
        Log.e("build", mUrl)
        return RestClient(mUrl, PARAMS, mSuccess, mError, mFailure, mRequest, mRequestBody, context, body, mFile, mDownloadDir, mExtension, mName,mLoadStyle)
    }

    companion object {
        private val PARAMS = RestCreator.PARAMS
    }
}
