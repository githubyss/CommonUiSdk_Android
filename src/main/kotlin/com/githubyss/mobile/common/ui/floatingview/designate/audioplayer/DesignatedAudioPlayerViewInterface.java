package com.githubyss.mobile.common.ui.floatingview.designate.audioplayer;


import com.githubyss.mobile.common.ui.audio.model.AudioModel;
import com.githubyss.mobile.common.ui.floatingview.feature.FeatureAutoShortenView;

import java.util.List;

import androidx.annotation.LayoutRes;


/**
 * DesignatedAudioPlayerViewInterface
 * <Description> 应用内悬浮窗接口方法
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/01/07 19:04:04
 */
public interface DesignatedAudioPlayerViewInterface {
    
    void lengthen();
    
    void shorten();
    
    void play(List<AudioModel> audioList);
    
    void play(List<AudioModel> audioList, boolean isNeedJumpToOverlayPermission);
    
    void start();
    
    void pause();
    
    void previous();
    
    void next();
    
    void switchVoice();
    
    void stop();
    
    AudioModel getCurrentAudio();
    
    boolean isFloatingShow();
    
    FeatureAutoShortenView getAutoShortedView();
    
    void customView(DesignatedAudioPlayerView viewGroup);
    
    void customView(@LayoutRes int resource);
    
    // void listener(DesignatedAudioPlayerViewListener designatedViewListener);
}
