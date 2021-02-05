package com.githubyss.mobile.common.ui.floatingview.audioplayer;


import com.githubyss.mobile.common.kit.manager.audio.model.AudioModel;


/**
 * ApiAudioPlayerFloatingListener
 * <Description> 音频播放器悬浮窗回调监听
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/09 11:34:40
 */
public interface ApiAudioPlayerFloatingListener {
    
    void onUpdateAudioInfo(AudioModel audioModel);
}
