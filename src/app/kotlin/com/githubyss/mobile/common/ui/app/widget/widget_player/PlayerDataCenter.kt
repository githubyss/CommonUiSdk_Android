package com.githubyss.mobile.common.ui.app.widget.widget_player

import com.githubyss.mobile.common.ui.app.widget.widget_schedule.ScheduleData
import com.githubyss.mobile.common.ui.app.widget.widget_schedule.ScheduleDataCenter.scheduleList


/**
 * PlayerDataCenter
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/09/05 16:17:41
 */
object PlayerDataCenter {
    val playList by lazy { buildPlayData() }

    /**  */
    private fun buildPlayData(): ArrayList<PlayerData> {
        val playList = ArrayList<PlayerData>()
        // playList.add(PlayerData("合肥-青岛三日自驾游"))
        // playList.add(PlayerData("小川洋风料理"))
        return playList
    }

    fun clear() {
        playList.clear()
    }
}
