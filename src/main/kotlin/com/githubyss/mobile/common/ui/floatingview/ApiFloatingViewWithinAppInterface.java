package com.githubyss.mobile.common.ui.floatingview;


import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.githubyss.mobile.common.ui.audio.model.AudioModel;
import com.githubyss.mobile.common.ui.floatingview.audioplayer.ApiAudioPlayerFloatingView;
import com.githubyss.mobile.common.ui.floatingview.audioplayer.BaseAutoShortedFloatingView;
import com.githubyss.mobile.common.ui.floatingview.audioplayer.DesignatedAudioPlayerFloatingView;
import com.githubyss.mobile.common.ui.floatingview.audioplayer.DesignatedAudioPlayerFloatingViewListener;

import java.util.List;

import androidx.annotation.LayoutRes;


/**
 * ApiFloatingViewWithinAppInterface
 * <Description> 应用内悬浮窗接口方法
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/01/07 19:04:04
 */
public interface ApiFloatingViewWithinAppInterface {
    
    ApiFloatingViewWithinApp show();
    
    void close();
    
    void attach(Activity activity);
    
    void attach(FrameLayout container);
    
    void detach(Activity activity);
    
    void detach(FrameLayout container);
    
    void layoutParams(ViewGroup.LayoutParams params);
}
