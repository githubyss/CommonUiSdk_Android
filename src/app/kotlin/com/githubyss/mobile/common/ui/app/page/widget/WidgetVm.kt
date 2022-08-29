package com.githubyss.mobile.common.ui.app.page.widget

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.githubyss.mobile.common.kit.util.getStringFromRes
import com.githubyss.mobile.common.ui.R


/**
 * WidgetVm
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/28 23:30:05
 */
class WidgetVm : ViewModel() {

    /** ****************************** Properties ****************************** */

    /**  */
    var title by mutableStateOf<String>(getStringFromRes(R.string.comui_time_countdown_title))
        private set

    /**  */
    var date by mutableStateOf("")
        private set


    /** ****************************** Functions ****************************** */

    /**  */
    fun changeTitle(title: String) {
        this.title = title
    }

    /**  */
    fun changeDate(date: String) {
        this.date = date
    }
}
