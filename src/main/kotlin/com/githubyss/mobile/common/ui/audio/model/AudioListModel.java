package com.githubyss.mobile.common.ui.audio.model;

import com.githubyss.mobile.common.ui.audio.enumeration.VoiceType;

import java.io.Serializable;
import java.util.List;


/**
 * AudioInfo
 * <Description> 音频信息列表数据结构
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/10 11:04:00
 */
public class AudioListModel implements Serializable {
    private int currentIndex;
    private List<AudioModel> audioList;
    
    public static VoiceType savedVoiceType = VoiceType.MALE;
    
    
    public AudioListModel setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
        return this;
    }
    
    public AudioListModel setAudioList(List<AudioModel> audioList) {
        this.audioList = audioList;
        return this;
    }
    
    public int getCurrentIndex() {
        return currentIndex;
    }
    
    public List<AudioModel> getAudioList() {
        return audioList;
    }
}
