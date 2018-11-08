package com.yirong.core.net

import com.yirong.core.app.ConfigType
import com.yirong.core.app.Suger

import java.util.ArrayList
import java.util.concurrent.TimeUnit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.Builder

/**
 * Created by yiron on 2018/10/30.
 */

object OKHttpHolder {
    val TIME = 60
    val BUILDER = Builder()
    val INTERCEPTORS = Suger.getSugerConfig(ConfigType.INTERCEPTOR.name) as ArrayList<Interceptor>?

    private val OK_HTTP_CLIENT = addInterceptor().connectTimeout(TIME.toLong(), TimeUnit.SECONDS).build()

    private fun addInterceptor(): Builder {
        if (INTERCEPTORS != null && !INTERCEPTORS.isEmpty()) {
            for (interceptor in INTERCEPTORS) {
                BUILDER.addInterceptor(interceptor)
            }
        }
        return BUILDER
    }


}
