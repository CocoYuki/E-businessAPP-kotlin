package com.yirong.core.delegate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.Unbinder
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment

/**
 * Created by yiron on 2018/10/30.
 */
abstract class BaseDelegate:SwipeBackFragment() {
    var mUnbinder:Unbinder? = null
    abstract fun setLayout():Any
    abstract fun onBindView(savedInstanceState: Bundle?,view:View)


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var rootView:View? = null
        if(setLayout()is Int){
            rootView = inflater!!.inflate(setLayout()as Int,container,false)
        }else if(setLayout()is View){
            rootView = setLayout()as View
        }
        if(rootView!=null){
            mUnbinder = ButterKnife.bind(rootView)
            onBindView(savedInstanceState,rootView)
        }
        return rootView
    }

    override fun onDestroy() {
        if(mUnbinder!=null){
            mUnbinder!!.unbind()
        }
        super.onDestroy()
    }
}