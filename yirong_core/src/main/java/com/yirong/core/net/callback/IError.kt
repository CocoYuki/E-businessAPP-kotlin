package com.yirong.core.net.callback

/**
 * Created by yiron on 2018/10/24.
 */

interface IError {

    fun onError(code: Int, msg: String)
}
