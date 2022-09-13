package com.githubyss.mobile.common.ui.app.widget.widget_schedule

import com.githubyss.mobile.common.kit.util.currentDatetimeString
import com.githubyss.mobile.common.kit.util.logD
import com.githubyss.mobile.common.ui.app.widget.widget_schedule.ScheduleDataCenter.datetime
import com.githubyss.mobile.common.ui.app.widget.widget_schedule.ScheduleDataCenter.scheduleList


/**
 * ScheduleDataCenter
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/29 00:36:09
 */
object ScheduleDataCenter {

    /** ****************************** Properties ****************************** */

    /**  */
    val TAG: String = ScheduleDataCenter::class.java.simpleName

    /**  */
    val scheduleList by lazy { ArrayList<ScheduleData>() }

    /**  */
    var datetime = ""
        get() = field.also { logD(TAG, "datetime: $it") }

    var weekday = ""
        get() = field.also { logD(TAG, "weekday: $it") }


    /** ****************************** Functions ****************************** */

    /**  */
    fun setupDate() {
        datetime = currentDatetimeString("M月d日")
        weekday = currentDatetimeString("EEEE")
        buildScheduleList()
    }

    fun buildScheduleList() {
        scheduleList.clear()
        scheduleList.add(ScheduleData("10:30", "合肥-青岛三日自驾游"))
        scheduleList.add(ScheduleData("11:30", "小川洋风料理"))
        scheduleList.add(ScheduleData("15:00", "帆船探险"))
    }

    /**  */
    fun clearScheduleList() {
        scheduleList.clear()
    }

    /**  */
    fun changeDatetime() {
        datetime = currentDatetimeString("M月d日 HH:mm:ss")
    }
}
