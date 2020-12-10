package com.githubyss.mobile.common.ui.audio.model;

import java.io.Serializable;
import java.util.List;

/**
 * 88396251
 * 2018/5/21.
 */

public class AudioInfo implements Serializable {
    public int currentIndex;
    private List<MusicModel> audioInfoList;

    public int getCurrentIndex() {
        return currentIndex;
    }

    public AudioInfo setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
        return this;
    }

    public List<MusicModel> getAudioInfoList() {
        return audioInfoList;
    }

    public AudioInfo setAudioInfoList(List<MusicModel> audioInfoList) {
        this.audioInfoList = audioInfoList;
        return this;
    }
}
