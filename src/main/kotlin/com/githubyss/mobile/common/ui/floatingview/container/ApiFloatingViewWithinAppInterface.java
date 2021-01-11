package com.githubyss.mobile.common.ui.floatingview.container;


import android.app.Activity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.githubyss.mobile.common.ui.floatingview.designate.audioplayer.DesignatedAudioPlayerView;


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
    
    DesignatedAudioPlayerView getDesignatedView();
}
