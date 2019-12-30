package com.wug.framew.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.fragment.app.Fragment
import com.wug.framew.util.Helper.internalStartActivity
import com.wug.framew.util.InterFieldMethod
import com.wug.framew.util.ThreadUtil

abstract class BaseFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return initView()
    }

    abstract fun initView(): View?

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initListener()
        loadData()
    }

    open fun loadData() {
    }

    open fun initListener() {

    }

    fun init() {

    }

    fun mToast(msg: String) {
        activity?.runOnUiThread {
            makeText(context, msg, Toast.LENGTH_SHORT).show()
        }
    }

    inline fun <reified T : Activity> Fragment.wStartActivity(vararg params: Pair<String, Any?>) =
            internalStartActivity(activity!!, T::class.java, params)

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