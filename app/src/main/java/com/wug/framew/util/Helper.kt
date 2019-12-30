package com.wug.framew.util

import android.app.Activity
import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

object Helper {
    inline fun <reified T : Activity> Context.wStartActivity(vararg params: Pair<String, Any?>) =
            internalStartActivity(this, T::class.java, params)

    inline fun <reified T : Activity> Activity.wStartActivityForResult(requestCode: Int, vararg params: Pair<String, Any?>) =
            internalStartActivityForResult(this, T::class.java, requestCode, params)

    inline fun <reified T : Service> Context.wStartService(vararg params: Pair<String, Any?>) =
            internalStartService(this, T::class.java, params)

    inline fun <reified T : Service> Context.wStopService(vararg params: Pair<String, Any?>) =
            internalStopService(this, T::class.java, params)

    inline fun <reified T : Activity> Fragment.wStartActivity(vararg params: Pair<String, Any?>) =
            internalStartActivity(activity!!, T::class.java, params)

    fun internalStartService(
            ctx: Context,
            service: Class<out Service>,
            params: Array<out Pair<String, Any?>>
    ): ComponentName? = ctx.startService(createIntent(ctx, service, params))

    @JvmStatic
    fun internalStopService(
            ctx: Context,
            service: Class<out Service>,
            params: Array<out Pair<String, Any?>>
    ): Boolean = ctx.stopService(createIntent(ctx, service, params))

    fun internalStartActivityForResult(
            act: Activity,
            activity: Class<out Activity>,
            requestCode: Int,
            params: Array<out Pair<String, Any?>>
    ) {
        act.startActivityForResult(createIntent(act, activity, params), requestCode)
    }

    fun internalStartActivity(ctx: Context, activity: Class<out Activity>, params: Array<out Pair<String, Any?>>) {
        ctx.startActivity(createIntent(ctx, activity, params))
    }

    private fun <T> createIntent(ctx: Context, clazz: Class<out T>, params: Array<out Pair<String, Any?>>): Intent {
        val intent = Intent(ctx, clazz)
        if (params.isNotEmpty()) intent.putExtras(bundleOf(*params))
        return intent
    }

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
}