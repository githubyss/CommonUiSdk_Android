package com.githubyss.mobile.common.ui.app.widget.widget_schedule


/**
 * ScheduleDataCenter
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/29 00:36:09
 */
object ScheduleDataCenter {
    val scheduleList by lazy { buildScheduleData() }

    /**  */
    private fun buildScheduleData(): ArrayList<ScheduleData> {
        val scheduleList = ArrayList<ScheduleData>()
        scheduleList.add(ScheduleData("10:30", "合肥-青岛三日自驾游"))
        scheduleList.add(ScheduleData("11:30", "小川洋风料理"))
        return scheduleList
    }
}
