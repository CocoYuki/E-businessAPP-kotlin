package com.yirong.myapplication

import android.os.Bundle
import com.joanzapata.iconify.fonts.FontAwesomeModule
import com.yirong.core.activity.ProxyActivity
import com.yirong.core.app.Suger
import com.yirong.core.delegate.SugerDelegate
import com.yirong.yirong_sc.FontEcModule
import me.yokeyword.fragmentation.SupportActivity

/**
 * Created by yiron on 2018/10/30.
 */
class ExampleActivity:ProxyActivity(){
    override fun getRootDelegate(): SugerDelegate {
        return ExampleDelegate()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Suger.init(this)
                .withIcons(FontEcModule())
                .withIcons(FontAwesomeModule())

                .configure()
    }
}