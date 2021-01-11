package com.githubyss.mobile.common.ui.floatingview.container.system;


import android.view.ViewGroup;


/**
 * ContainerFloatingWithinSystemInterface
 * <Description> 系统级别悬浮窗接口方法
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/01/11 14:54:59
 */
public interface ContainerFloatingWithinSystemInterface {
    
    ContainerFloatingWithinSystem show();
    
    void close();
    
    void layoutParams(ViewGroup.LayoutParams params);
    
    void displayWhenAppForeground();
    
    void hideWhenAppBackground();
}
