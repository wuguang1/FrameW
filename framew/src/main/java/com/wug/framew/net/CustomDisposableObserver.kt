package com.wug.framew.net

import android.app.ProgressDialog
import io.reactivex.observers.DisposableObserver


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
