package com.wug.framew.net

import android.app.ProgressDialog
import android.util.Log
import com.google.gson.Gson
import com.wug.framew.factory.mToast
import com.wug.framew.net.exception.HttpResultException
import io.reactivex.observers.DisposableObserver
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeoutException


class CustomDisposableObserver<T>(
    var l: HttpResultSubscriberListener,
    var mProgressDialog: ProgressDialog,
    var method: String,
    var isShow: Boolean
) : DisposableObserver<HttpResult<T>>() {
    override fun onComplete() {
        Log.d("net", " --> onCompleted")
    }

    override fun onNext(t: HttpResult<T>) {
        Log.d("net", " --> onNext --->${t.msg}")
        if (t.code == "0") {
            try {
                l.onSuccess(Gson().toJson(t.data), method)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else {
            l.onError(t.code, t.msg, Gson().toJson(t.data), method)
            if (t.msg != null && t.msg.isNotEmpty())
                mToast(t.msg)
        }
    }

    override fun onError(e: Throwable) {
        var code = -1000
        var msg: String? = e.message
        if (e is ConnectException
            || e is SocketTimeoutException
            || e is TimeoutException
        ) {
            code = -9999
            msg = "connect time out"

        } else if (e is HttpResultException) {
            code = e.code
            msg = e.msg
        }
        if (msg != null)
            mToast(msg)
        else
            mToast("请求服务器失败")
        e.printStackTrace()
        l.onError(code.toString(), e.message, "", "")
    }
}
