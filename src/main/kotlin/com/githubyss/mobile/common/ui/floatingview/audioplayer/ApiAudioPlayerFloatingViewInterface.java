package com.githubyss.mobile.common.ui.floatingview.audioplayer;


import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.githubyss.mobile.common.kit.manager.audio.model.AudioModel;

import java.util.List;

import androidx.annotation.LayoutRes;


/**
 * ApiAudioPlayerFloatingViewInterface
 * <Description> 音频播放器悬浮窗接口方法
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/09 11:34:40
 */
public interface ApiAudioPlayerFloatingViewInterface {
    
    ApiAudioPlayerFloatingView init(Context context);
    
    ApiAudioPlayerFloatingView show();
    
    void close();
    
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
    
    BaseAutoShortedFloatingView getAutoShortedView();
    
    void customView(DesignatedAudioPlayerFloatingView viewGroup);
    
    void customView(@LayoutRes int resource);
    
    void layoutParams(ViewGroup.LayoutParams params);
    
    void listener(DesignatedAudioPlayerFloatingViewListener designatedAudioPlayerFloatingViewListener);
    
    void attach(Activity activity);
    
    void attach(FrameLayout container);
    
    void detach(Activity activity);
    
    void detach(FrameLayout container);
}
