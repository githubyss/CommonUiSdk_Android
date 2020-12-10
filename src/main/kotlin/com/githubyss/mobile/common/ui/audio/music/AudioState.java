package com.githubyss.mobile.common.ui.audio.music;

/**
 * 88396251
 * 2018/5/22
 * 播放状态类
 */

public enum AudioState {

    STATE_START(0x15), STATE_PREPARE(0x25), STATE_PLAYING(0x35), STATE_STOP(0x45), STATE_PAUSE(0x55), STATE_READY(0x65), STATE_END(0x45);

    private int state;

    AudioState(int state) {
        this.state = state;
    }

}
