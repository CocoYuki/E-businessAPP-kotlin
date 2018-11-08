package com.yirong.core.net.callback

import android.util.Log
import com.yirong.core.ui.SugerLoader
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by yiron on 2018/10/25.
 */

class RequestCallbacks (private val mSuccess: ISuccess, private val mError: IError, private val mFailure: IFailure, private val mRequest: IRequest?, mLoadStyle: String): Callback<String> {


    override fun onFailure(call: Call<String>?, t: Throwable?) {
        SugerLoader.stopLoading()
        Log.i("failure",t.toString());
        if (mFailure != null) {
            mFailure.onFailure()
        }
        if (mRequest != null) {
            mRequest.onRequestFinish()
        }
    }

    override fun onResponse(call: Call<String>?, response: Response<String>?) {
        SugerLoader.stopLoading()
        if(response!=null&&call!=null){
            if(response!!.isSuccessful){
                if(call!!.isExecuted){
                    mSuccess.onSuccess(response.body()!!)
                }

            }else{
                if(mError !=null){
                    mError.onError(response.code(),response.message())
                }
            }
        }else{
            throw RuntimeException("in RequestCallbacks：the response/call is null！")
        }
    }
}
