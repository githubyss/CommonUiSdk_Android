package com.githubyss.mobile.common.ui.audio.model;

import com.githubyss.mobile.common.ui.audio.music.AudioState;

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
public class MusicModel implements Serializable {
    private String id;
    private String title;
    private String author;
    private String icon;
    private String url;
    private String maleUrl;
    private String femaleUrl;
    private AudioState playStatus;


    public MusicModel() {
    }

    public MusicModel(String id, String title, String author, String icon, String url, String maleUrl, String femaleUrl, AudioState playStatus) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.icon = icon;
        this.url = url;
        this.maleUrl = maleUrl;
        this.femaleUrl = femaleUrl;
        this.playStatus = playStatus;
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

    public AudioState getPlayStatus() {
        return playStatus;
    }

    public MusicModel setIcon(String icon) {
        this.icon = icon;
        return this;
    }
}
