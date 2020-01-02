package com.wug.framew.factory

import android.app.Activity
import android.app.ProgressDialog
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.wug.framew.R
import com.wug.framew.base.FBaseApplication
import com.wug.framew.net.CustomDisposableObserver
import com.wug.framew.net.HttpResult
import com.wug.framew.util.Helper
import com.wug.framew.util.InterFieldMethod
import com.wug.framew.util.ThreadUtil
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

inline fun <reified T : Activity> Context.wStartActivity(vararg params: Pair<String, Any?>) =
    Helper.internalStartActivity(this, T::class.java, params)

inline fun <reified T : Activity> Activity.wStartActivityForResult(
    requestCode: Int,
    vararg params: Pair<String, Any?>
) =
    Helper.internalStartActivityForResult(this, T::class.java, requestCode, params)

inline fun <reified T : Service> Context.wStartService(vararg params: Pair<String, Any?>) =
    Helper.internalStartService(this, T::class.java, params)

inline fun <reified T : Service> Context.wStopService(vararg params: Pair<String, Any?>) =
    Helper.internalStopService(this, T::class.java, params)

inline fun <reified T : Activity> Fragment.wStartActivity(vararg params: Pair<String, Any?>) =
    Helper.internalStartActivity(activity!!, T::class.java, params)

fun <T> Intent.get(key: String): T? {
    try {
        val extras = InterFieldMethod.mExtras.get(this) as Bundle
        InterFieldMethod.unparcel.invoke(extras)
        val map = InterFieldMethod.mMap.get(extras) as Map<String, Any>
        return map[key] as T
    } catch (e: Exception) {
    }
    return null
}

fun mToast(msg: String) {
    if (!TextUtils.isEmpty(msg))
        ThreadUtil.runOnMainThread(Runnable {
            Toast.makeText(FBaseApplication.getContext(), msg, Toast.LENGTH_SHORT).show()
        })
}
