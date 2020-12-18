package com.githubyss.mobile.common.ui.floatingview.audioplayer;

import android.app.Activity;
import android.content.Context;
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
    
    ApiAudioPlayerFloatingWindow init(Context context);
    
    ApiAudioPlayerFloatingWindow show();
    
    ApiAudioPlayerFloatingWindow close();
    
    ApiAudioPlayerFloatingWindow lengthen();
    
    ApiAudioPlayerFloatingWindow shorten();
    
    ApiAudioPlayerFloatingWindow play(List<AudioModel> audioList);
    
    ApiAudioPlayerFloatingWindow play(List<AudioModel> audioList, boolean isNeedJumpToOverlayPermission);
    
    ApiAudioPlayerFloatingWindow start();
    
    ApiAudioPlayerFloatingWindow pause();
    
    ApiAudioPlayerFloatingWindow previous();
    
    ApiAudioPlayerFloatingWindow next();
    
    ApiAudioPlayerFloatingWindow switchVoice();
    
    ApiAudioPlayerFloatingWindow stop();
    
    ApiAudioPlayerFloatingWindow displayWhenAppForeground();
    
    ApiAudioPlayerFloatingWindow hideWhenAppBackground();
    
    AudioModel getCurrentAudio();
    
    boolean isFloatingShow();
    
    BaseAutoShortedFloatingView getAutoShortedView();
    
    ApiAudioPlayerFloatingWindow customView(DesignatedAudioPlayerFloatingView viewGroup);
    
    ApiAudioPlayerFloatingWindow customView(@LayoutRes int resource);
    
    ApiAudioPlayerFloatingWindow layoutParams(ViewGroup.LayoutParams params);
    
    ApiAudioPlayerFloatingWindow listener(DesignatedAudioPlayerFloatingViewListener designatedAudioPlayerFloatingViewListener);
}
