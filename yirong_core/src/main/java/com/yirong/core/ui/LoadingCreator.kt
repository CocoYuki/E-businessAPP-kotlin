package com.yirong.core.ui

import android.content.ContentValues.TAG
import android.content.Context
import android.text.TextUtils
import android.util.Log
import com.wang.avi.AVLoadingIndicatorView
import com.wang.avi.Indicator

import java.util.*

/**
 * Created by yiron on 2018/11/7.
 */
object LoadingCreator{

    private var avLoadingIndicatorView:AVLoadingIndicatorView? = null
    val LOADING_MAP = WeakHashMap<String, Indicator>()
    fun create(context: Context,type:String):AVLoadingIndicatorView{
        avLoadingIndicatorView = AVLoadingIndicatorView(context)
        if(LOADING_MAP[type]==null){
            val indicator = getIndicator(type)
            if(indicator!=null){
                LOADING_MAP.put(type,indicator)
            }
        }
        avLoadingIndicatorView!!.indicator = LOADING_MAP[type]
        return avLoadingIndicatorView as AVLoadingIndicatorView
    }

    fun getIndicator(type: String):Indicator?{
        if (TextUtils.isEmpty(type)) {
            return null
        }
        val drawableClassName = StringBuilder()
        if (!type.contains(".")) {
//            val defaultPackageName = javaClass.`package`.name
            drawableClassName.append("com.wang.avi")
                    .append(".indicators")
                    .append(".")
        }
        drawableClassName.append(type)
        try {
            val drawableClass = Class.forName(drawableClassName.toString())
            val indicator = drawableClass.newInstance() as Indicator
            return indicator
        } catch (e: ClassNotFoundException) {
            Log.e(TAG, "Didn't find your class , check the name again !")
            return null
        } catch (e: InstantiationException) {
            e.printStackTrace()
            return null
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
            return null
        }

    }
}