package com.wug.framew.util

import android.os.Handler
import android.os.Looper

object ThreadUtil {
    private val handler = Handler(Looper.getMainLooper())
    fun runOnMainThread(runnable: Runnable) {
        handler.post(runnable)
    }
}