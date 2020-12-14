package com.githubyss.mobile.common.ui.floatingview.audioplayer;


import com.githubyss.mobile.common.ui.audio.model.AudioModel;

public interface DesignatedAudioPlayerFloatingViewListener {

    void onClose();
    
    void onUpdateAudioInfo(AudioModel audioModel);
}
