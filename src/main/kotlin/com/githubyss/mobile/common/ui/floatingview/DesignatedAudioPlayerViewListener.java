package com.githubyss.mobile.common.ui.floatingview;


import com.githubyss.mobile.common.ui.audio.model.AudioModel;


/**
 * DesignatedAudioPlayerViewListener
 * <Description> 特定的音频播放器悬浮窗回调监听
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/01/07 19:23:10
 */
public interface DesignatedAudioPlayerViewListener {
    
    void onUpdateAudioInfo(AudioModel audioModel);
}
