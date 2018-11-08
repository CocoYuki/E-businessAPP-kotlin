package com.yirong.core.app

import android.content.Context

/**
* Created by yiron on 2018/10/29.
*/
object Suger{

        fun init(context:Context): Configurations {
            Configurations.SUGER_CONFIG.put(ConfigType.APPLICATION_CONTEXT.name, context)
            return Configurations
        }

        fun getSugerContext():Context{
            return Configurations.SUGER_CONFIG[ConfigType.APPLICATION_CONTEXT.name] as Context
        }

        fun getSugerConfig(key:String):Any?{
            return Configurations.SUGER_CONFIG[key]
        }



}