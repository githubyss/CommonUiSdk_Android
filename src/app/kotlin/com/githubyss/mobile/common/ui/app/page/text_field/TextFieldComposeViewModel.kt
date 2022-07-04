package com.githubyss.mobile.common.ui.app.page.text_field

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import com.githubyss.mobile.common.kit.util.getStringFromRes
import com.githubyss.mobile.common.ui.R


/**
 * TextFieldComposeViewModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/06/29 00:12:15
 */
class TextFieldComposeViewModel : ViewModel() {

    /** ****************************** Properties ****************************** */

    private val titleDefault = getStringFromRes(R.string.comui_text_field_compose_title)
    private val textDefault = ""
    private val labelDefault = "密码"
    private val placeholderDefault = "请输入密码"
    private val isErrorDefault = false

    /** 数据绑定，绑定到 UI 的字段（data field） */
    var title: String by mutableStateOf(titleDefault)
        private set

    var text: String by mutableStateOf(textDefault)
        private set

    var label: String by mutableStateOf(labelDefault)
        private set

    var placeholder: String by mutableStateOf(placeholderDefault)
        private set

    var isError: Boolean by mutableStateOf(isErrorDefault)
        private set


    /** ****************************** Functions ****************************** */

    fun updateTitle(title: String) {
        this.title = title
    }

    fun updateText(text: String) {
        this.text = text
    }

    fun updateLabel(isError: Boolean) {
        if (this.text.isEmpty()) {
            this.label = labelDefault
            return
        }

        if (isError) {
            this.label = "密码格式错误，请输入纯数字"
        }
        else {
            this.label = labelDefault
        }
    }

    fun validateDigits(text: String) {
        this.isError = !(text.isEmpty() || text.isDigitsOnly())
    }
}