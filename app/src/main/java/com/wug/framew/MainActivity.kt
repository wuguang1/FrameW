package com.wug.framew

import android.widget.Toast
import com.wug.framew.base.BaseActivity


class MainActivity : BaseActivity() {

    override fun getLayoutID(): Int = R.layout.activity_main

    override fun initView() {
        Toast.makeText(context, "", Toast.LENGTH_SHORT)
        startActivity<BaseActivity>("1" to 1, "2" to 2)
    }
}
