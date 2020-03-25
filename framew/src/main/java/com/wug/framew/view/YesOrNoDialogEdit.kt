package com.wug.framew.view

import android.app.Dialog
import android.content.Context
import android.view.View
import com.wug.framew.R
import kotlinx.android.synthetic.main.item_yesornodialog.tv_YesOrNo_info
import kotlinx.android.synthetic.main.item_yesornodialog.tv_YesOrNo_left
import kotlinx.android.synthetic.main.item_yesornodialog.tv_YesOrNo_right
import kotlinx.android.synthetic.main.item_yesornodialog_edit.*

/**
 * @author wg
 */
class YesOrNoDialogEdit : Dialog {

    constructor(context: Context) : this(context, 0)
    constructor(context: Context, themeResId: Int) : super(context, R.style.loadingDialogStyle) {
        setContentView(R.layout.item_yesornodialog_edit)

    }

    fun setTextValue(info: String, left: String = context.resources.getString(R.string.str_chance), right: String = context.resources.getString(R.string.str_sure)) {
        if (info.isNotEmpty()) tv_YesOrNo_info.text = info
        if (left.isNotEmpty()) tv_YesOrNo_left.text = left
        if (right.isNotEmpty()) tv_YesOrNo_right.text = right
    }

    fun getEditContext(): String {
        return edit_dialog.text.toString()
    }

    fun setOnclickListener(listener: View.OnClickListener) {
        tv_YesOrNo_left.setOnClickListener(listener)
        tv_YesOrNo_right.setOnClickListener(listener)
//        tv_YesOrNo_center.setOnClickListener(listener)
    }
}
