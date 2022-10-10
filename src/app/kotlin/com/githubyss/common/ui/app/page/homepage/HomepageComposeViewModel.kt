package com.githubyss.common.ui.app.page.homepage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.githubyss.common.kit.util.getStringFromRes
import com.githubyss.common.ui.R


/**
 * HomepageComposeViewModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/03/30 17:50:22
 */
class HomepageComposeViewModel : ViewModel() {

    /** ****************************** Properties ****************************** */

    /**  */
    private val titleDefault = getStringFromRes(R.string.comui_homepage_title)
    val playControllerPlay = getStringFromRes(R.string.comres_audio_player_play)
    val playControllerPause = getStringFromRes(R.string.comres_audio_player_pause)

    /**  */
    var title: String by mutableStateOf(titleDefault)
        private set

    /**  */
    var playController: String by mutableStateOf(playControllerPlay)
        private set


    /** ****************************** Functions ****************************** */

    /**  */
    fun changeTitle(title: String) {
        this.title = title
    }

    /**  */
    fun switchPlayText(text: String) {
        this.playController = text
    }
}