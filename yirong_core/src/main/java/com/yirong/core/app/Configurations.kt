package com.yirong.core.app

import android.util.Log
import com.joanzapata.iconify.IconFontDescriptor
import com.joanzapata.iconify.Iconify
import okhttp3.Interceptor


/**
* Created by yiron on 2018/10/29.
*/
object Configurations {
    val SUGER_CONFIG = HashMap<String,Any>()
    private  val ICON_LISTS = ArrayList<IconFontDescriptor>();
    private val INTERCEPTORS = ArrayList<Interceptor>()
    init {

        SUGER_CONFIG[ConfigType.CONFIG_READY.name] = false;
    }

    fun withAPIHost(host:String): Configurations {
        SUGER_CONFIG[ConfigType.API_HOST.name] = host
        return this
    }

    fun configure(){
        initIcons()
        SUGER_CONFIG[ConfigType.CONFIG_READY.name] = true;
    }

    private fun initIcons(){
        if (ICON_LISTS.size>0){
            val initializer = Iconify.with(ICON_LISTS.get(0))
            for(i in 1 until ICON_LISTS.size){
                initializer.with(ICON_LISTS[i])
            }
        }
    }

    fun withIcons(descriptor: IconFontDescriptor): Configurations {
        ICON_LISTS.add(descriptor)
        return this
    }
    fun checkConfig(){
        val isReady = SUGER_CONFIG.get(ConfigType.CONFIG_READY.name) as Boolean
        if(isReady){
            Log.i("configurations","success finished")
        }else{
            throw RuntimeException("config is not finished,please config")
        }
    }



    fun withInterceptor(interceptor: Interceptor): Configurations {
        INTERCEPTORS.add(interceptor)
        SUGER_CONFIG.put(ConfigType.INTERCEPTOR.name, INTERCEPTORS)
        return this
    }

    fun withInterceptors(interceptors: java.util.ArrayList<Interceptor>): Configurations {
        INTERCEPTORS.addAll(interceptors)
        SUGER_CONFIG.put(ConfigType.INTERCEPTOR.name, INTERCEPTORS)
        return this
    }

    internal fun <T> getConfiguration(key: Enum<ConfigType>): T {
        checkConfig()
        return SUGER_CONFIG[key.name] as T
    }


}