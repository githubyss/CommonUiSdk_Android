package com.githubyss.mobile.common.ui.toolbar.classical

import androidx.annotation.DrawableRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.githubyss.mobile.common.ui.R


/**
 * NormalToolbarVm
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/22 14:34:04
 */
class NormalToolbarVm : ViewModel() {

    /** ****************************** Object ****************************** */

    /**  */
    companion object {
        val TEXT_DEFAULT = ""
        val ACTION_LEFT_DEFAULT = R.drawable.comres_icon_close_no_border
        val ACTION_RIGHT_DEFAULT = R.drawable.comres_icon_close_no_border
    }


    /** ****************************** Properties ****************************** */

    /** 数据绑定，绑定到 UI 的字段（data field） */
    val text by lazy { MutableLiveData<String>(TEXT_DEFAULT) }
    val actionLeft by lazy { MutableLiveData<Int>(ACTION_LEFT_DEFAULT) }
    val actionRight by lazy { MutableLiveData<Int>(ACTION_RIGHT_DEFAULT) }


    /** ****************************** Override ****************************** */

    /**  */
    override fun onCleared() {
        super.onCleared()
        clearData()
    }


    /** ****************************** Functions ****************************** */

    /**  */
    fun setupData(text: String, @DrawableRes actionLeft: Int, @DrawableRes actionRight: Int) {
        this.text.value = text
        this.actionLeft.value = actionLeft
        this.actionRight.value = actionRight
    }

    /**  */
    fun clearData() {
        this.text.value = null
        this.actionLeft.value = null
        this.actionRight.value = null
    }
}
