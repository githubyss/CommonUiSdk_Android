package com.githubyss.mobile.common.ui.audio.util;


import com.githubyss.mobile.common.ui.audio.music.AudioState;

/**
 * 88396251
 * 2018/5/23
 * 播放状态
 */

public interface MusicInterface {


    /**
     * 当前状态改变
     */
    void onStateChanged(AudioState audioState);

    /**
     * 当前播放进度
     */
    void onPlayProgress(int CurrentPosition);

    /**
     * 当前缓存进度
     */
    void onBufferingUpdateProgress(int percent);

}
