package com.wug.framew.util

import android.app.Activity
import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.core.os.bundleOf
import java.io.Serializable

object Helper {
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

}