package com.wug.framew.base

import android.app.Application
import android.content.Context

open class FBaseApplication : Application() {
    companion object {
        private var mContext: Application? = null
        fun getContext(): Context {
            return mContext!!
        }

    }

    override fun onCreate() {
        super.onCreate()
        mContext = this
    }
}