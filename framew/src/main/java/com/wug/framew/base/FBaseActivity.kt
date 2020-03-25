package com.wug.framew.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wug.framew.net.HttpResultSubscriberListener
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T


abstract class FBaseActivity : AppCompatActivity(), HttpResultSubscriberListener {
    val context: FBaseActivity get() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        keepScreenOn()
        setContentView(getLayoutID())
        initView()
        initListener()
        loaddata()
    }

    abstract fun getLayoutID(): Int

    open fun loaddata() {

    }

    open fun initView() {

    }

    open fun initListener() {

    }

    open fun keepScreenOn(){

    }
}