package com.wug.framew.base

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.wug.framew.net.HttpResultSubscriberListener


abstract class FBaseFragment : Fragment(), HttpResultSubscriberListener {
    //    lateinit var handler: MHandler
    var fCallBackListener: FCallBackListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initF()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = getLayoutId()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initListener()
        loadData()
    }

    abstract fun getLayoutId(): View?

    open fun initView() {}

    open fun loadData() {
    }

    open fun OnKeyBack() {
    }

    open fun initListener() {
    }

    private fun initF() {
        if (activity is FCallBackListener)
            fCallBackListener = activity as FCallBackListener

        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    OnKeyBack()
                }
            })
    }

    open fun disposeMsg(type: Int, obj: Any) {}

    fun finish() {
        if (this.activity != null) {
            activity?.finish()
        }
    }

    private fun removeFragment(fragment: FBaseFragment) {
        val parent = this.activity
        val fragmentTransaction = parent?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.remove(fragment)
        fragmentTransaction?.commit()
    }

    interface FCallBackListener {
        fun updateToolbar(
            title: String = "",  // 标题内容
            color: Int = Color.BLACK, //标题颜色
            bgcolor: Int = Color.WHITE,//背景颜色
            back_resid: Int = 0 //返回按钮resid
        )
    }

    override fun onDestroy() {
//        Frame.HANDLES.remove(this.handler)
        super.onDestroy()
    }

}