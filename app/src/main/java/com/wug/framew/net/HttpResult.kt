package com.wug.test.util

data class HttpResult<T>(val code: String, val data: T, val msg: String)