package com.githubyss.mobile.common.ui.floatingview.container;


import android.content.Context;
import android.view.ViewGroup;

import com.githubyss.mobile.common.ui.audio.model.AudioModel;
import com.githubyss.mobile.common.ui.floatingview.audioplayer.ApiAudioPlayerFloatingWindow;
import com.githubyss.mobile.common.ui.floatingview.audioplayer.BaseAutoShortedFloatingView;
import com.githubyss.mobile.common.ui.floatingview.audioplayer.DesignatedAudioPlayerFloatingView;
import com.githubyss.mobile.common.ui.floatingview.audioplayer.DesignatedAudioPlayerFloatingViewListener;

import java.util.List;

import androidx.annotation.LayoutRes;


/**
 * ApiFloatingWindowWithinSystemInterface
 * <Description> 系统级别悬浮窗接口方法
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/01/11 14:54:59
 */
public interface ApiFloatingWindowWithinSystemInterface {
    
    ApiFloatingWindowWithinSystem show();
    
    void close();
    
    void layoutParams(ViewGroup.LayoutParams params);
    
    void displayWhenAppForeground();
    
    void hideWhenAppBackground();
}
