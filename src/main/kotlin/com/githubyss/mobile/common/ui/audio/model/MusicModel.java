package com.githubyss.mobile.common.ui.audio.model;

import java.io.Serializable;

/**
 * 88396251
 * 2018/5/21
 */

public class MusicModel implements Serializable {
    private String title;
    private String author;
    private String icon;
    private String url;
    private String maleUrl;
    private String femaleUrl;


    public MusicModel() {
    }

    public MusicModel(String author, String title, String icon, String maleUrl, String femaleUrl) {
        this.title = title;
        this.author = author;
        this.icon = icon;
        this.url = url;
        this.maleUrl = maleUrl;
        this.femaleUrl = femaleUrl;
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

    public MusicModel setIcon(String icon) {
        this.icon = icon;
        return this;
    }
}
