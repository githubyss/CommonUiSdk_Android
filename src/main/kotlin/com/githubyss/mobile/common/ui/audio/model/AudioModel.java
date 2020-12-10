package com.githubyss.mobile.common.ui.audio.model;

import android.text.TextUtils;

import com.githubyss.mobile.common.ui.audio.music.AudioState;
import com.githubyss.mobile.common.ui.audio.music.VoiceType;

import java.io.Serializable;


/**
 * MusicModel
 * <Description> 音频信息数据结构
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/10 10:59:10
 */
public class AudioModel implements Serializable {
    private String id;
    private String title;
    private String author;
    private String icon;
    private String url;
    private String maleUrl;
    private String femaleUrl;
    private AudioState audioState;
    private VoiceType voiceType;

    private VoiceType savedVoiceType = VoiceType.MALE;

    public AudioModel() {
    }

    public AudioModel(String id, String title, String author, String icon, String url, String maleUrl, String femaleUrl, AudioState audioState) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.icon = icon;
        this.url = url;
        this.maleUrl = maleUrl;
        this.femaleUrl = femaleUrl;
        this.audioState = audioState;
        this.voiceType = savedVoiceType;
        processProperty();
    }

    private void processProperty() {
        switch (this.voiceType) {
            case MALE:
                if (!TextUtils.isEmpty(this.maleUrl)) {
                    this.url = this.maleUrl;
                } else {
                    this.url = this.femaleUrl;
                }
                break;
            case FEMALE:
                if (!TextUtils.isEmpty(this.femaleUrl)) {
                    this.url = this.femaleUrl;
                } else {
                    this.url = this.maleUrl;
                }
                break;
            default:
                break;
        }
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIcon() {
        return icon;
    }

    public String getUrl() {
        return url;
    }

    public String getMaleUrl() {
        return maleUrl;
    }

    public String getFemaleUrl() {
        return femaleUrl;
    }

    public AudioState getAudioState() {
        return audioState;
    }

    public VoiceType getVoiceType() {
        return voiceType;
    }

    public AudioModel setIcon(String icon) {
        this.icon = icon;
        return this;
    }
}
