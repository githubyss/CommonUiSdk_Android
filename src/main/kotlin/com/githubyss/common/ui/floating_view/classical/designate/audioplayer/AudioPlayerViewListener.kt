package com.githubyss.common.ui.floating_view.classical.designate.audioplayer

import com.githubyss.common.kit.manager.audio_player.model.AudioModel


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
