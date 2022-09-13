package com.githubyss.mobile.common.ui.app.widget.widget_player

import com.githubyss.mobile.common.kit.util.logD


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
        get() = field.also { logD(TAG, "title: $it") }


    /** ****************************** Functions ****************************** */

    /**  */
    fun setupDate() {
        title = ""
        buildPlayList()
    }

    /**  */
    fun buildPlayList() {
        playList.clear()
        playList.add(PlayData("最新！新增本⼟356例，本⼟⽆症状4272例"))
        playList.add(PlayData("这些⼈，⽤⻘春为中国核电造“芯”"))
    }

    fun clearPlayList() {
        playList.clear()
    }

    fun changeTitle(title: String) {
        this.title = title
    }
}
