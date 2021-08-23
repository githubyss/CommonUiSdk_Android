package com.githubyss.mobile.common.ui.floating_view.designate.audioplayer

import androidx.annotation.LayoutRes
import com.githubyss.mobile.common.kit.manager.audio.model.AudioModel


/**
 * AudioPlayerViewInterface
 * 音频播放器 View 接口方法
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/02/22 18:56:08
 */
interface AudioPlayerViewInterface {
    fun lengthen()
    fun shorten()
    fun play(audioList: List<AudioModel?>?)
    fun start()
    fun pause()
    fun previous()
    fun next()
    fun stop()
    fun switchVoice()
    fun customView(view: AudioPlayerViewAutoShorten?)
    fun customView(@LayoutRes layoutId: Int)
}
