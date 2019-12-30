package com.wug.framew.net

data class HttpResult<T>(val code: String, val data: T, val msg: String)