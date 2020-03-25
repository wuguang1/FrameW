package com.wug.framew.factory

import android.app.Activity
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.wug.framew.R
import com.wug.framew.base.FBaseApplication
import com.wug.framew.util.Helper
import com.wug.framew.util.InterFieldMethod
import com.wug.framew.util.ThreadUtil
import java.util.*

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
    if (msg.isNotEmpty())
        ThreadUtil.runOnMainThread(Runnable {
            Toast.makeText(FBaseApplication.getContext(), msg, Toast.LENGTH_SHORT).show()
        })
}

fun <T> data2Model(data: String?, mclass: Class<T>): T? {
    return Gson().fromJson(data, mclass)
}

fun <T> data2List(json: String?, cls: Class<T>): List<T> {
    val list = ArrayList<T>()
    try {
        val array = JsonParser().parse(json).asJsonArray
        for (element in array) {
            list.add(Gson().fromJson(element, cls))
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return list
}

fun putShareP(context: Context, key: String, value: String) {
    val sp = context.getSharedPreferences(
        context.getString(
            R.string.app_name
        ), Context.MODE_PRIVATE
    )
    sp.edit {
        putString(key, value)
    }
}

fun putShareP(context: Context, key: String, value: Int) {
    val sp = context.getSharedPreferences(
        context.getString(
            R.string.app_name
        ), Context.MODE_PRIVATE
    )
    sp.edit {
        putInt(key, value)
    }
}

fun getShareP(context: Context, key: String): String? {
    val sp = context.getSharedPreferences(
        context.getString(
            R.string.app_name
        ), Context.MODE_PRIVATE
    )
    return sp.getString(key, "")
}
