package com.wug.test.util

interface HttpResultSubscriberListener {
    fun onSuccess(data: String?, method: String)
    fun onError(code: String?, msg: String?, data: String?, method: String)
}