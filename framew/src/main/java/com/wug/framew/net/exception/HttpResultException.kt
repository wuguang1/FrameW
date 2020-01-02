package com.wug.framew.net.exception

import java.io.IOException

class HttpResultException(val code: Int, val msg: String) : IOException()