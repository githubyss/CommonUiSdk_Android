package com.githubyss.mobile.common.ui.floatingview.audioplayer;

import android.view.ViewGroup;

import com.githubyss.mobile.common.ui.audio.model.AudioModel;

import java.util.List;

import androidx.annotation.LayoutRes;


/**
 * ApiAudioPlayerFloatingWindowListener
 * <Description> 对外回调
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/09 11:34:40
 */
public interface ApiAudioPlayerFloatingWindowListener {
    
    void onUpdateAudioInfo(AudioModel audioModel);
}
