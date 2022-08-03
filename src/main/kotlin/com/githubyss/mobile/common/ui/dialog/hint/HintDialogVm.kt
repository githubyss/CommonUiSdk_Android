package com.githubyss.mobile.common.ui.dialog.hint

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


/**
 * HintDialogVm
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/02 15:37:33
 */
class HintDialogVm : ViewModel() {

    /** ****************************** Companion ****************************** */

    /**  */
    companion object {
        const val TITLE_DEFAULT = ""
        const val CONTENT_DEFAULT = ""
        const val BTN_LEFT_DEFAULT = "确认"
        const val BTN_RIGHT_DEFAULT = "取消"
    }


    /** ****************************** Properties ****************************** */

    /** 数据绑定，绑定到 UI 的字段（data field） */
    val title by lazy { MutableLiveData<String>() }
    val content by lazy { MutableLiveData<String>() }
    val btnLeft by lazy { MutableLiveData<String>() }
    val btnRight by lazy { MutableLiveData<String>() }


    /** ****************************** Override ****************************** */

    /**  */
    override fun onCleared() {
        super.onCleared()
        clearData()
    }


    /** ****************************** Functions ****************************** */

    /** ******************** Data Handling ******************** */

    /**  */
    fun setupData(title: String, content: String, btnConfirm: String, btnCancel: String) {
        this.title.value = title
        this.content.value = content
        this.btnLeft.value = btnConfirm
        this.btnRight.value = btnCancel
    }

    /**  */
    fun clearData() {
    }
}
