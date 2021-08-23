package com.githubyss.mobile.common.ui.floating_view.container

import com.githubyss.mobile.common.kit.manager.audio.model.AudioModel


/**
 * FloatingAudioPlayerListener
 * 应用级 Floating 回调监听（音频播放器）
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/02/23 14:44:11
 */
interface FloatingAudioPlayerListener {
    fun onShow()
    fun onRemove()
    fun onUpdateAudioInfo(audioModel: AudioModel?)
}
