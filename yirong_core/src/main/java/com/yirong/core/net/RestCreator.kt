package com.yirong.core.net


import com.yirong.core.app.ConfigType
import com.yirong.core.app.Suger
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

/**
* Created by yiron on 2018/10/31.
*/
class RestCreator  {

companion object {
    var PARAMS = WeakHashMap<String, Any>()
    fun getRestService(): RestService {
        return RestServiceHolder.REST_SERVICE
    }
}


    private object RetrofitHolder {
        val BASE_URL = Suger.getSugerConfig((ConfigType.API_HOST.name)) as String
        val RETROFIT_CLIENT = Retrofit.Builder()
                .baseUrl(BASE_URL)


                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())//ScalarsConverterFactory返回一个原汁原味的参数，没经过任何处理
                .build()

    }

//    private object OKHttpHolder {
//        private val TIME_OUT = 60
//        private val BUILDER = OkHttpClient.Builder()
//
//        val INTERCEPTORS = Suger.getSugerConfig((ConfigType.INTERCEPTOR.name) as ArrayList<Interceptor>
//
//
//
//    }

   object OKHttpHolder {
        private val TIME_OUT = 60
        private val BUILDER = OkHttpClient.Builder()

        private val INTERCEPTORS = Suger.getSugerConfig(ConfigType.INTERCEPTOR.name) as ArrayList<Interceptor>

        private fun addInterceptor(): OkHttpClient.Builder {
            if (INTERCEPTORS != null && !INTERCEPTORS.isEmpty()) {
                for (interceptor in INTERCEPTORS) {
                    BUILDER.addInterceptor(interceptor)
                }
            }
            return BUILDER
        }

       val OK_HTTP_CLIENT = addInterceptor()
               .connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
               .build()

    }
    private object RestServiceHolder {
        val REST_SERVICE = RetrofitHolder.RETROFIT_CLIENT.create(RestService::class.java)
    }

}