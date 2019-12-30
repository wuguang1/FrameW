package com.wug.framew

import android.widget.Toast
import com.wug.framew.base.BaseActivity


class MainActivity : BaseActivity() {

    override fun getLayoutID(): Int = R.layout.activity_main

    override fun initView() {
        Toast.makeText(context, "", Toast.LENGTH_SHORT)
    }
}
