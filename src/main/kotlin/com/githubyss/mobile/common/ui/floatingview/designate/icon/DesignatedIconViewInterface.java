package com.githubyss.mobile.common.ui.floatingview.designate.icon;


import com.githubyss.mobile.common.ui.floatingview.feature.FeatureMagnetView;

import androidx.annotation.DrawableRes;
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
public interface DesignatedIconViewInterface {
    
    void icon(@DrawableRes int resId);
    
    void customView(DesignatedIconView viewGroup);
    
    void customView(@LayoutRes int resource);
    
    boolean isFloatingShow();
    
    DesignatedIconView getDesignatedView();
    
    FeatureMagnetView getMagnetView();
}
