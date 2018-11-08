package com.yirong.core.ui

import android.content.Context
import android.support.v7.app.AppCompatDialog
import android.view.Gravity
import com.wang.avi.AVLoadingIndicatorView
import com.yirong.core.R
import com.yirong.core.utils.DimenUtils
import java.util.ArrayList

/**
 * Created by yiron on 2018/11/7.
 */
object SugerLoader{
    private val LOAD_SIZE_SCALE = 8
    private val LOAD_OFFSET_SCALE = 10

    private val LOADERS = ArrayList<AppCompatDialog>()

    private val DEFAULT_LOADER = LoadingStyle.BallSpinFadeLoaderIndicator.name

    fun showloader(context:Context,type:String){
        val dialog:AppCompatDialog = AppCompatDialog(context, R.style.dialog)
        val loadingView:AVLoadingIndicatorView = LoadingCreator.create(context,type)
        dialog.setContentView(loadingView)
        val deviceHeight = DimenUtils.getScreenHeight()
        val deviceWidth = DimenUtils.getScreenWidth()

        val dialogWindow = dialog.window

        if (dialogWindow != null) {
            val lp = dialogWindow.attributes
            lp.width = deviceWidth / LOAD_SIZE_SCALE
            lp.height = deviceHeight / LOAD_SIZE_SCALE
            lp.height = lp.height + deviceHeight / LOAD_OFFSET_SCALE
            lp.gravity = Gravity.CENTER
        }
        LOADERS.add(dialog)
        dialog.show()
    }

    fun showloader(context: Context) {
        showloader(context, DEFAULT_LOADER)
    }

    fun stopLoading() {
        for (dialog in LOADERS) {
            if (dialog.isShowing) {
                dialog.cancel()
                dialog.dismiss()
            }
        }
        LOADERS.clear()
    }
}