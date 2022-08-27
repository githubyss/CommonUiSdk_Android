package com.githubyss.mobile.common.ui.app.widget.widget_schedule

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
    val date by lazy { MutableLiveData<String>("") }
    val weekday by lazy { MutableLiveData<String>("") }
    val scheduleList by lazy { MutableLiveData(ArrayList<ScheduleData>()) }


    /** ****************************** Functions ****************************** */

    /**  */
    fun setupDate() {
        val dateValue = currentDatetimeString("M月d日")
        val weekdayValue = currentDatetimeString("EEEE")
        date.value = dateValue
        weekday.value = weekdayValue
    }
}
