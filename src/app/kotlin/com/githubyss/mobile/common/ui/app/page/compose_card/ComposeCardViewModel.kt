package com.githubyss.mobile.common.ui.app.page.compose_card

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.githubyss.mobile.common.kit.util.getStringFromRes
import com.githubyss.mobile.common.ui.R


/**
 * ComposeCardViewModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/06/20 15:47:17
 */
class ComposeCardViewModel : ViewModel() {

    /** ****************************** Properties ****************************** */

    private val titleDefault = getStringFromRes(R.string.comui_compose_card_title)

    /** 数据绑定，绑定到 UI 的字段（data field） */
    var title: String by mutableStateOf(titleDefault)
        private set


    /** ****************************** Functions ****************************** */

    fun changeTitle(title: String) {
        this.title = title
    }
}