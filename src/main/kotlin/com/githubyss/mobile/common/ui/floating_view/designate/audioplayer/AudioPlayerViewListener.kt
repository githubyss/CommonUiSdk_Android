package com.githubyss.mobile.common.ui.floating_view.designate.audioplayer

import com.githubyss.mobile.common.kit.manager.audio_player.model.AudioModel


/**
 * AudioPlayerViewListener
 * 音频播放器 View 回调监听
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/02/22 18:52:25
 */
interface AudioPlayerViewListener {
    fun onUpdateAudioInfo(audioModel: AudioModel?)
}
