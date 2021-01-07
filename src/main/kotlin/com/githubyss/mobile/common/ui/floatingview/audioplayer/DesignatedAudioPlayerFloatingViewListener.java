package com.githubyss.mobile.common.ui.floatingview.audioplayer;


import com.githubyss.mobile.common.ui.audio.model.AudioModel;


/**
 * DesignatedAudioPlayerFloatingViewListener
 * <Description> 特定的音频播放器悬浮窗回调监听
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/01/06 11:08:16
 */
public interface DesignatedAudioPlayerFloatingViewListener {

    void onClose();
    
    void onUpdateAudioInfo(AudioModel audioModel);
}
