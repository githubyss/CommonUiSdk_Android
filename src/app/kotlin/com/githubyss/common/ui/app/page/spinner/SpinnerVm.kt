package com.githubyss.common.ui.app.page.spinner

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.githubyss.common.kit.util.getStringFromRes
import com.githubyss.common.ui.R


/**
 * SpinnerVm
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/09/04 19:32:35
 */
class SpinnerVm : ViewModel() {

    /** ****************************** Properties ****************************** */

    /**  */
    var title by mutableStateOf<String>(getStringFromRes(R.string.comui_spinner_title))
        private set


    /** ****************************** Functions ****************************** */

    /**  */
    fun changeTitle(title: String) {
        this.title = title
    }
}
