package com.githubyss.mobile.common.ui.audio.util;


import com.githubyss.mobile.common.ui.audio.music.AudioState;

/**
 * MusicInterface
 * <Description> 播放状态
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/10 17:59:28
 */
public interface MusicInterface {

    /**
     * 当前状态改变
     */
    void onStateChanged(AudioState audioState);

    /**
     * 当前播放进度
     */
    void onPlayProgress(int currentPosition);

    /**
     * 当前缓存进度
     */
    void onBufferingUpdateProgress(int percent);
}
