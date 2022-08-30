package com.githubyss.mobile.common.ui.app.widget.widget_schedule

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.githubyss.mobile.common.kit.util.currentDatetimeString


/**
 * ScheduleWidgetVm
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/25 13:40:41
 */
class ScheduleWidgetVm : ViewModel() {

    /** ****************************** Properties ****************************** */

    /**  */
    val TAG = ScheduleWidgetVm::class.java.simpleName

    /**  */
    var date by mutableStateOf("")
        private set

    /**  */
    val weekday by lazy { MutableLiveData<String>("") }

    /**  */
    val scheduleList by lazy { MutableLiveData(ArrayList<ScheduleData>()) }


    /** ****************************** Functions ****************************** */

    /**  */
    fun setupDate() {
        date = currentDatetimeString("M月d日")
        weekday.value = currentDatetimeString("EEEE")
    }

    /**  */
    fun changeDate() {
        date = currentDatetimeString("yyyy年M月d日")
    }
}
