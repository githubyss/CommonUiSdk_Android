package com.githubyss.mobile.common.ui.audio.model;

import java.io.Serializable;
import java.util.List;


/**
 * AudioInfo
 * <Description> 音频列表信息
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/10 11:04:00
 */
public class AudioInfo implements Serializable {
    public int currentIndex;
    private List<MusicModel> audioList;

    public int getCurrentIndex() {
        return currentIndex;
    }

    public AudioInfo setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
        return this;
    }

    public List<MusicModel> getAudioList() {
        return audioList;
    }

    public AudioInfo setAudioList(List<MusicModel> audioList) {
        this.audioList = audioList;
        return this;
    }
}
