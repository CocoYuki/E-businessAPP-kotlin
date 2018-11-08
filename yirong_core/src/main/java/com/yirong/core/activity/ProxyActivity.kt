package com.yirong.core.activity

import android.os.Bundle
import android.support.v7.widget.ContentFrameLayout
import com.yirong.core.R
import com.yirong.core.delegate.SugerDelegate
import me.yokeyword.fragmentation.SupportActivity

/**
 * Created by yiron on 2018/10/30.
 */
abstract class ProxyActivity:SupportActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initContainer(savedInstanceState)
    }

    private fun initContainer(savedInstanceState: Bundle?) {
        val layout = ContentFrameLayout(this)
        layout.id= R.id.delegate_container
        setContentView(layout)
        if(savedInstanceState==null){
            loadRootFragment(R.id.delegate_container,getRootDelegate())
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        System.gc()
        System.runFinalization()
    }

    abstract fun getRootDelegate(): SugerDelegate

}