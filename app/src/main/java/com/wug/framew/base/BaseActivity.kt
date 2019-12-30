package com.wug.framew.base

import android.app.Activity
import android.app.Service
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.wug.framew.util.Helper
import com.wug.framew.util.Helper.internalStartActivity
import com.wug.framew.util.Helper.internalStartActivityForResult
import com.wug.framew.util.Helper.internalStartService
import com.wug.framew.util.Helper.internalStopService


abstract class BaseActivity : AppCompatActivity() {
    val context: BaseActivity get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutID())
        initView()
        initListener()
        loaddata()
    }

    open fun loaddata() {

    }

    open fun initView() {

    }

    open fun initListener() {

    }

    abstract fun getLayoutID(): Int

    fun mToast(msg: String) {
        runOnUiThread {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }
}