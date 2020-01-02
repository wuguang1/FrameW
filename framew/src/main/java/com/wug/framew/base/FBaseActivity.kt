package com.wug.framew.base

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wug.framew.R
import com.wug.framew.factory.mToast
import com.wug.framew.net.CustomDisposableObserver
import com.wug.framew.net.HttpResult
import com.wug.framew.net.HttpResultSubscriberListener
import com.wug.framew.util.AppUtil
import com.wug.framew.util.Helper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

abstract class FBaseActivity : AppCompatActivity(), HttpResultSubscriberListener {
    val context: FBaseActivity get() = this
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutID())
        initView()
        initListener()
        loaddata()
    }

    open fun loaddata() {

    }

    open fun initView() {

    }

    open fun initListener() {

    }

    abstract fun getLayoutID(): Int

    fun <T> load(o: Observable<HttpResult<T>>, m: String, isShow: Boolean = true) {
        if (!AppUtil.isNetworkAvailable(FBaseApplication.getContext())) {
            mToast(getString(R.string.net_error))
        } else {
            var s = CustomDisposableObserver<T>(
                    this,
                    ProgressDialog(context).apply { this.setMessage(getString(R.string.loading)) },
                    m,
                    isShow
            )
            compositeDisposable.add(s)
            o.subscribeOn(Schedulers.newThread()).unsubscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe { if (s.isShow) s.mProgressDialog.show() }
                    .doFinally { if (s.mProgressDialog.isShowing) s.mProgressDialog.dismiss() }.subscribe(s)
        }
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}