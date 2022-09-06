package com.githubyss.mobile.common.ui.app.widget.widget_player

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.githubyss.mobile.common.kit.util.currentDatetimeString
import com.githubyss.mobile.common.ui.app.widget.widget_schedule.ScheduleData


/**
 * PlayerWidgetVm
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/09/05 16:19:50
 */
class PlayerWidgetVm : ViewModel() {

    /** ****************************** Properties ****************************** */

    /**  */
    val TAG = PlayerWidgetVm::class.java.simpleName

    /**  */
    var playingTitle by mutableStateOf("")
        private set

    /**  */
    val playerList by lazy { MutableLiveData(ArrayList<PlayerData>()) }


    /** ****************************** Functions ****************************** */

    /**  */
    fun setupDate() {
        playingTitle = currentDatetimeString("M月d日")
    }
}
