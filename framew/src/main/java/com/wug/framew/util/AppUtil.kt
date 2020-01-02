package com.wug.framew.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object AppUtil {
    /**
     * 描述：判断网络是否有效.
     *
     * @param context the context
     * @return true, if is network available
     */
    fun isNetworkAvailable(context: Context): Boolean {
        try {
            val connectivity = context
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivity != null) {
                val info = connectivity.activeNetworkInfo
                if (info != null && info.isConnected) {
                    if (info.state == NetworkInfo.State.CONNECTED) {
                        return true
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }

        return false
    }
}