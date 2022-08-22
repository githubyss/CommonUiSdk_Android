package com.githubyss.mobile.common.ui.app.page.time_countdown

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.githubyss.mobile.common.kit.util.getStringFromRes
import com.githubyss.mobile.common.ui.R


/**
 * TimeCountdownViewModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/22 15:35:01
 */
class TimeCountdownViewModel : ViewModel() {

    /** ****************************** Properties ****************************** */

    /** 数据绑定，绑定到 UI 的字段（data field） */
    var title by mutableStateOf<String>(getStringFromRes(R.string.comui_time_countdown_title))
        private set

    val remainingMillisecond by lazy { MutableLiveData<Long>(300000) }


    /** ****************************** Functions ****************************** */

    /**  */
    fun changeTitle(title: String) {
        this.title = title
    }
}
