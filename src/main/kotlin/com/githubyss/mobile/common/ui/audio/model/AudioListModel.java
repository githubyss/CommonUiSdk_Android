package com.githubyss.mobile.common.ui.audio.model;

import java.io.Serializable;
import java.util.List;


/**
 * AudioInfo
 * <Description> 音频列表信息数据结构
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/10 11:04:00
 */
public class AudioListModel implements Serializable {
    public int currentIndex;
    private List<AudioModel> audioList;

    public int getCurrentIndex() {
        return currentIndex;
    }

    public AudioListModel setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
        return this;
    }

    public List<AudioModel> getAudioList() {
        return audioList;
    }

    public AudioListModel setAudioList(List<AudioModel> audioList) {
        this.audioList = audioList;
        return this;
    }
}
