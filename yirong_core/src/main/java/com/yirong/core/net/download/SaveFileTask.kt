package com.yirong.core.net.download

/**
 * Created by yiron on 2018/10/30.
 */


import android.content.Intent
import android.net.Uri
import android.os.AsyncTask


import com.yirong.core.app.Suger
import com.yirong.core.net.callback.IRequest
import com.yirong.core.net.callback.ISuccess
import com.yirong.core.utils.file.FileUtils

import java.io.File
import java.io.InputStream

import okhttp3.ResponseBody


internal class SaveFileTask(private val REQUEST: IRequest?, private val SUCCESS: ISuccess?) : AsyncTask<Any, Void, File>() {

    override fun doInBackground(vararg params: Any): File {
        var downloadDir: String? = params[0] as String
        var extension: String? = params[1] as String
        val body = params[2] as ResponseBody
        var name = params[3] as String
        val `is` = body.byteStream()
        if (downloadDir == null || downloadDir == "") {
            downloadDir = "down_loads"
        }
        if (extension == null || extension == "") {
            extension = ""
        }
        return if (name == null) {
            FileUtils.writeToDisk(`is`, downloadDir, extension.toUpperCase(), extension)
        } else {
            FileUtils.writeToDisk(`is`, downloadDir, name)
        }
    }

    override fun onPostExecute(file: File) {
        super.onPostExecute(file)
        SUCCESS?.onSuccess(file.path)
        REQUEST?.onRequestFinish()
        autoInstallApk(file)
    }

    private fun autoInstallApk(file: File) {
        if (FileUtils.getExtension(file.path) == "apk") {
            val install = Intent()
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            install.action = Intent.ACTION_VIEW
            install.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive")
            Suger.getSugerContext().startActivity(install)
        }
    }
}