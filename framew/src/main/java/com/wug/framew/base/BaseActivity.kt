package com.wug.framew.base

import android.app.Activity
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.wug.framew.util.Helper.internalStartActivity
import com.wug.framew.util.Helper.internalStartActivityForResult
import com.wug.framew.util.Helper.internalStartService
import com.wug.framew.util.Helper.internalStopService
import com.wug.framew.util.InterFieldMethod


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

    inline fun <reified T : Activity> Context.wStartActivity(vararg params: Pair<String, Any?>) =
            internalStartActivity(this, T::class.java, params)

    inline fun <reified T : Activity> Activity.wStartActivityForResult(requestCode: Int, vararg params: Pair<String, Any?>) =
            internalStartActivityForResult(this, T::class.java, requestCode, params)

    inline fun <reified T : Service> Context.wStartService(vararg params: Pair<String, Any?>) =
            internalStartService(this, T::class.java, params)

    inline fun <reified T : Service> Context.wStopService(vararg params: Pair<String, Any?>) =
            internalStopService(this, T::class.java, params)

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