package com.githubyss.mobile.common.ui.floatingview.audioplayer;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.LayoutRes;

import com.githubyss.mobile.common.ui.audio.model.AudioModel;

import java.util.List;


/**
 * ApiAudioPlayerFloatingWindowInterface
 * <Description> 接口方法规整
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/09 11:34:40
 */
public interface ApiAudioPlayerFloatingWindowInterface {
    
    ApiAudioPlayerFloatingWindow show();
    
    ApiAudioPlayerFloatingWindow close();
    
    ApiAudioPlayerFloatingWindow lengthen();
    
    ApiAudioPlayerFloatingWindow shorten();
    
    ApiAudioPlayerFloatingWindow start();
    
    ApiAudioPlayerFloatingWindow pause();
    
    ApiAudioPlayerFloatingWindow previous();
    
    ApiAudioPlayerFloatingWindow next();
    
    ApiAudioPlayerFloatingWindow switchVoice();
    
    ApiAudioPlayerFloatingWindow stop();
    
    ApiAudioPlayerFloatingWindow initData(List<AudioModel> audioList);
    
    AudioModel getCurrentAudio();
    
    boolean isFloatingShow();
    
    BaseAutoShortedFloatingView getAutoShortedView();
    
    ApiAudioPlayerFloatingWindow customView(DesignatedAudioPlayerFloatingView viewGroup);
    
    ApiAudioPlayerFloatingWindow customView(@LayoutRes int resource);
    
    ApiAudioPlayerFloatingWindow layoutParams(ViewGroup.LayoutParams params);
    
    ApiAudioPlayerFloatingWindow listener(DesignatedAudioPlayerFloatingViewListener designatedAudioPlayerFloatingViewListener);
}
