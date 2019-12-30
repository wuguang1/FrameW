package com.wug.test.util

import android.app.ProgressDialog
import android.util.Log
import com.google.gson.Gson
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
    }

    override fun onNext(t: HttpResult<T>) {
    }

    override fun onError(e: Throwable) {
    }
}
