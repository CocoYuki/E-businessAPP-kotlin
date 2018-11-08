package com.yirong.myapplication

import android.os.Bundle
import android.view.View
import com.yirong.core.delegate.SugerDelegate

/**
 * Created by yiron on 2018/10/30.
 */
class ExampleDelegate:SugerDelegate() {
    override fun setLayout(): Any {
        return R.layout.activity_main
    }

    override fun onBindView(savedInstanceState: Bundle?, view: View) {
       //网络请求
    }

}