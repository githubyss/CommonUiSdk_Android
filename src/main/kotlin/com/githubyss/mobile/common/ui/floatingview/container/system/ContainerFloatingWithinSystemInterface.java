package com.githubyss.mobile.common.ui.floatingview.container.system;


import android.graphics.drawable.Drawable;
import android.view.ViewGroup;

import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;


/**
 * ContainerFloatingWithinSystemInterface
 * <Description> 系统级别悬浮窗接口方法
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/01/11 14:54:59
 */
public interface ContainerFloatingWithinSystemInterface<C, D, F> {
    
    C show();
    
    void close();
    
    void layoutParams(ViewGroup.LayoutParams params);
    
    C setMovable(boolean isMovable);
    
    C customIcon(Drawable drawable);
    
    C customIcon(@DrawableRes int drawableId);
    
    C customView(D viewGroup);
    
    C customView(@LayoutRes int layoutId);
    
    D getDesignatedView();
    
    F getFeatureView();
    
    void refreshViewWhenAppForeground();
    
    void refreshViewWhenAppBackground();
}
