package com.wug.framew.view

import android.app.Dialog
import android.content.Context
import android.view.View
import com.wug.framew.R
import kotlinx.android.synthetic.main.item_yesornodialog.*

/**
 * @author wg
 */
class YesOrNODialog : Dialog {

    constructor(context: Context) : this(context, 0)
    constructor(context: Context, themeResId: Int) : super(context, R.style.loadingDialogStyle) {
        setContentView(R.layout.item_yesornodialog)

    }

    fun setTextValue(info: String, left: String = context.resources.getString(R.string.str_chance), right: String = context.resources.getString(R.string.str_sure)) {
        if (info.isNotEmpty()) tv_YesOrNo_info.text = info
        if (left.isNotEmpty()) tv_YesOrNo_left.text = left
        if (right.isNotEmpty()) tv_YesOrNo_right.text = right
    }

//    fun setSingleBtn(info: String, center: String = context.resources.getString(R.string.str_ckxq)) {
//        if (info.isNotEmpty()) tv_YesOrNo_info.text = info
//        tv_YesOrNo_left.visibility = GONE
//        tv_YesOrNo_right.visibility = GONE
//        tv_YesOrNo_center.visibility = VISIBLE
//    }

    fun setOnclickListener(listener: View.OnClickListener) {
        tv_YesOrNo_left.setOnClickListener(listener)
        tv_YesOrNo_right.setOnClickListener(listener)
//        tv_YesOrNo_center.setOnClickListener(listener)
    }
}
