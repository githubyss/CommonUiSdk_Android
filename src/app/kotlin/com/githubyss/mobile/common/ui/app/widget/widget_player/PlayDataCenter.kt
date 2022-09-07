package com.githubyss.mobile.common.ui.app.widget.widget_player

import com.githubyss.mobile.common.kit.util.logD
import com.githubyss.mobile.common.ui.app.widget.widget_schedule.ScheduleDataCenter


/**
 * PlayDataCenter
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/09/05 16:17:41
 */
object PlayDataCenter {

    /** ****************************** Properties ****************************** */

    /**  */
    val TAG: String = PlayDataCenter::class.java.simpleName

    /**  */
    val playList by lazy { ArrayList<PlayData>() }

    /**  */
    var title = ""
        get() = field.also { logD(ScheduleDataCenter.TAG, "title: $it") }


    /** ****************************** Functions ****************************** */

    /**  */
    fun setupDate() {
        title = ""
    }

    /**  */
    fun buildPlayList() {
        playList.clear()
        playList.add(PlayData("合肥-青岛三日自驾游"))
        playList.add(PlayData("小川洋风料理"))
    }

    fun clearPlayList() {
        playList.clear()
    }

    fun changeTitle(title: String) {
        this.title = title
    }
}
