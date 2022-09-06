package com.githubyss.mobile.common.ui.app.widget.widget_schedule


/**
 * ScheduleDataCenter
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/29 00:36:09
 */
object ScheduleDataCenter {
    val scheduleList by lazy { ArrayList<ScheduleData>() }

    /**  */
    fun clear() {
        scheduleList.clear()
    }
}
